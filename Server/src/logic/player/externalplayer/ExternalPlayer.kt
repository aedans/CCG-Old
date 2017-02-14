package logic.player.externalplayer

import connection.Connection
import logic.action.Target
import logic.action.targetFromString
import logic.player.Deck
import logic.player.Player
import logic.player.PlayerAction
import java.util.*

/**
 * Created by Aedan Smith.
 */

val REQUEST_ACTION = "a"
val REQUEST_TARGET = "t"
val DRAW_CARD = "d"
val DISCARD_CARD = "D"
val PLAY_CARD = "b"
val KILL_CARD = "B"

class ExternalPlayer(private val connection: Connection) : Player(loadDeck(connection), ExternalHand(connection), ExternalBoard(connection)) {
    override fun nextAction(): PlayerAction {
        connection.getOutput().println(REQUEST_ACTION)
        val s = connection.getInput().next()
        when (s[0]) {
            'a' -> return PlayerAction(PlayerAction.ActionType.DO_ACTION, s.substring(1))
            'e' -> return PlayerAction(PlayerAction.ActionType.END_TURN, "")
            else -> throw RuntimeException("Could not find action \"" + s + "\"")
        }
    }

    override fun requestTarget(targets: ArrayList<String>): Target {
        var string = REQUEST_TARGET
        for (target in targets) {
            string += target
        }
        connection.getOutput().println(string)
        return targetFromString(connection.getInput().next())
    }
}

fun loadDeck(connection: Connection): Deck {
    val deck = Deck()
    for (s in connection.getInput().next().split(",".toRegex()).dropLastWhile(String::isEmpty).toTypedArray()) {
        deck.add(0, s)
    }
    return deck
}
