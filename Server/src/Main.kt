import server.open
import logic.game.StandardGame
import logic.player.Player
import logic.player.externalplayer.ExternalPlayer
import java.util.*

/**
 * Created by Aedan Smith.
 */

fun main(args: Array<String>) {
    val connections = open(Integer.parseInt(args[0]), Integer.parseInt(args[1]))

    val players = ArrayList<Player>()
    for (connection in connections) {
        players.add(ExternalPlayer(connection))
    }
    val standardGame = StandardGame(players)
    standardGame.run()

    for (connection in connections) {
        connection.close()
    }
}
