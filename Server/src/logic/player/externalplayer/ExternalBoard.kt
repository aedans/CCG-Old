package logic.player.externalplayer

import connection.Connection
import connection.KILL_CARD
import connection.PLAY_CARD
import logic.cards.Permanent
import logic.player.Board
import java.util.*

/**
 * Created by Aedan Smith.
 */

class ExternalBoard(private val connection: Connection) : Board {
    private val permanents = ArrayList<Permanent>()

    override fun add(permanent: Permanent) {
        connection.getOutput().println(PLAY_CARD + permanent.id)
        permanents.add(permanent)
    }

    override fun remove(n: Int): String {
        connection.getOutput().println(KILL_CARD + (n + 33).toChar())
        return permanents.removeAt(n).id
    }

    override val board = permanents
}
