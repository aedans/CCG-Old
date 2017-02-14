package logic.action

import logic.cards.Permanent
import logic.game.Game
import logic.player.externalplayer.commands

/**
 * Created by Aedan Smith.
 */

interface Target {
    fun get(game: Game): Permanent
}

fun targetFromString(s: String): Target {
    if (s.startsWith(commands.getString("BOARD"))){
        return object : Target {
            val l = s.substring(commands.getString("BOARD").length)
            val playerIndex = l.substring(0, l.indexOf(':')).toInt()
            val boardIndex = l.substring(l.indexOf(':')+1).toInt()

            override fun get(game: Game): Permanent {
                return game.players[playerIndex].board.board[boardIndex]
            }
        }
    }
    throw RuntimeException("Could not find target \"" + s + "\"")
}
