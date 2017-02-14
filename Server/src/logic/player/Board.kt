package logic.player

import logic.cards.Permanent

/**
 * Created by Aedan Smith.
 */

interface Board {
    fun add(permanent: Permanent)

    fun remove(n: Int): String

    val board: MutableList<Permanent>

    fun applyStateBasedActions() {
        board.removeAll { it.health <= 0 }
    }
}
