package logic.player.externalplayer

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import logic.action.Target
import logic.action.targetFromString
import logic.player.Deck
import logic.player.Player
import logic.player.PlayerAction
import server.Connection
import java.io.FileReader
import java.util.*

/**
 * Created by Aedan Smith.
 */

val commands: JSONObject by lazy {
    JSON.parseObject(FileReader("assets/server.json").readText())!!
}

class ExternalPlayer(private val connection: Connection) : Player(loadDeck(connection), ExternalHand(connection), ExternalBoard(connection)) {
    override fun nextAction(): PlayerAction {
        connection.getOutput().println(commands.getString("REQUEST_ACTION"))
        val s = connection.getInput().next()
        if (s.startsWith(commands.getString("DO_ACTION"))) {
            return PlayerAction(PlayerAction.ActionType.DO_ACTION, s.substring(commands.getString("DO_ACTION").length))
        }
        if (s == commands.getString("END_TURN")) {
            return PlayerAction(PlayerAction.ActionType.END_TURN, "")
        }
        throw RuntimeException("Could not find action \"$s\"")
    }

    override fun requestTarget(targets: ArrayList<String>): Target {
        var string = commands.getString("REQUEST_TARGET")
        for (target in targets) {
            string += target + commands.getString("TARGET_SEPARATOR")
        }
        string = string.substring(0, string.length-commands.getString("TARGET_SEPARATOR").length)
        connection.getOutput().println(string)
        return targetFromString(connection.getInput().next())
    }
}

fun loadDeck(connection: Connection): Deck {
    val deck = Deck()
    for (s in connection.getInput().next().split(commands.getString("DECK_SEPARATOR").toRegex()).dropLastWhile(String::isEmpty).toTypedArray()) {
        deck.add(0, s)
    }
    return deck
}
