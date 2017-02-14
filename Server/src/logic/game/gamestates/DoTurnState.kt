package logic.game.gamestates

import logic.game.Game
import logic.game.GameState
import logic.player.Player
import logic.player.PlayerAction

/**
 * Created by Aedan Smith.
 */

class DoTurnState(val player: Player) : GameState {
    override fun apply(game: Game): GameState {
        while (true){
            game.resolveStack()
            val playerAction = player.nextAction()
            if (playerAction.actionType == PlayerAction.ActionType.END_TURN){
                return game.endTurnStates[game.indexOf(player)]
            }
            if (playerAction.actionType == PlayerAction.ActionType.DO_ACTION){
                game.get(playerAction.value).apply(game, player)
            }
        }
    }
}
