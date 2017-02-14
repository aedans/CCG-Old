package logic.game.gamestates

import logic.game.Game
import logic.game.GameState

/**
 * Created by Aedan Smith.
 */

class BeginGameState : GameState {
    override fun apply(game: Game): GameState {
        game.players.forEach {
            for (i in 0..1){
                it.draw()
            }
        }
        game.resolveStack()
        return game.beginTurnStates[0]
    }
}
