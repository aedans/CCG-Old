package logic.player.externalplayer

import connection.Connection
import connection.DISCARD_CARD
import connection.DRAW_CARD
import logic.player.Hand
import java.util.*

/**
 * Created by Aedan Smith.
 */

class ExternalHand(private val connection: Connection) : Hand {
    private val cards = ArrayList<String>()

    override fun add(s: String) {
        connection.getOutput().println(DRAW_CARD + s)
        cards.add(s)
    }

    override fun remove(n: Int): String {
        connection.getOutput().println(DISCARD_CARD + (n + 33).toChar())
        return cards.removeAt(n)
    }

    override fun get(n: Int): String {
        return cards[n]
    }

    override val hand = cards
}
