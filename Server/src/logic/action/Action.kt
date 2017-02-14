package logic.action

import logic.game.Game

/**
 * Created by Aedan Smith.
 */

interface Action {
    fun apply(game: Game)
}
