package logic.game

import logic.player.Player

/**
 * Created by Aedan Smith.
 */

interface GameAction {
    fun apply(game: Game, current: Player)
}
