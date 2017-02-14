
import logic.game.Game
import logic.player.Player
import logic.player.externalplayer.ExternalPlayer
import server.open
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
    val game = Game(players)

    game.run()
    for (connection in connections) {
        connection.close()
    }
}
