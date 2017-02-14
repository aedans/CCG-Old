package logic.game

/**
 * Created by Aedan Smith.
 */

interface GameState {
    fun apply(game: Game): GameState
}
