package logic.game.gamestates

import logic.game.Game
import logic.game.GameState
import logic.player.Player

/**
 * Created by Aedan Smith.
 */

class EndTurnState(val player: Player) : GameState {
    override fun apply(game: Game): GameState {
        var index = game.indexOf(player)+1
        if (index >= game.players.size){
            index = 0
        }
        return game.beginTurnStates[index]
    }
}
