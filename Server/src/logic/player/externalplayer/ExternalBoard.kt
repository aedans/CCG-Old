package logic.player.externalplayer

import logic.cards.Permanent
import logic.player.Board
import server.Connection
import java.util.*

/**
 * Created by Aedan Smith.
 */

class ExternalBoard(private val connection: Connection) : Board {
    private val permanents = ArrayList<Permanent>()

    override fun add(permanent: Permanent) {
        connection.getOutput().println(commands.getString("PLAY_CARD") + permanent.id)
        permanents.add(permanent)
    }

    override fun remove(n: Int): String {
        connection.getOutput().println(commands.getString("KILL_CARD") + n)
        return permanents.removeAt(n).id
    }

    override val board = permanents
}
