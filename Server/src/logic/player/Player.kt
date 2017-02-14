package logic.player

import logic.action.Target
import java.util.*

/**
 * Created by Aedan Smith.
 */

abstract class Player(var deck: Deck, var hand: Hand, var board: Board) {
    fun draw() {
        if (!deck.isEmpty()) {
            hand.add(deck.pop())
        }
    }

    abstract fun nextAction(): PlayerAction

    abstract fun requestTarget(targets: ArrayList<String>): Target
}
