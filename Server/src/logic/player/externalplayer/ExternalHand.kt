package logic.player.externalplayer

import logic.player.Hand
import server.Connection
import java.util.*

/**
 * Created by Aedan Smith.
 */

class ExternalHand(private val connection: Connection) : Hand {
    private val cards = ArrayList<String>()

    override fun add(s: String) {
        connection.getOutput().println(commands.getString("DRAW_CARD") + s)
        cards.add(s)
    }

    override fun remove(n: Int): String {
        connection.getOutput().println(commands.getString("DISCARD_CARD") + n)
        return cards.removeAt(n)
    }

    override fun get(n: Int): String {
        return cards[n]
    }

    override val hand = cards
}
