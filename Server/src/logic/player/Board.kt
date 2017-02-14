package logic.player

import logic.cards.Permanent
import java.util.*

/**
 * Created by Aedan Smith.
 */

interface Board {
    fun add(permanent: Permanent)

    fun remove(n: Int): String

    val board: List<Permanent>

    fun applyStateBasedActions() {
        val toRemove = ArrayList<Permanent>()
        for (permanent in board) {
            if (permanent.health <= 0) {
                toRemove.add(permanent)
            }
        }
        for (permanent in toRemove) {
            remove(board.indexOf(permanent))
        }
    }
}
