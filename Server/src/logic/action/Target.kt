package logic.action

import logic.cards.Permanent
import logic.game.Game

/**
 * Created by Aedan Smith.
 */

interface Target {
    fun get(game: Game): Permanent
}

fun targetFromString(s: String): Target {
    when (s[0]) {
        'b' -> return object : Target {
            override fun get(game: Game): Permanent {
                return game.players[s[1].toInt() - 33].board.board[s[2].toInt() - 33]
            }
        } else -> throw RuntimeException("Could not find target \"" + s + "\"")
    }
}
