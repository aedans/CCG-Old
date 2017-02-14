package logic.game.gamestates

import logic.game.Game
import logic.game.GameState
import logic.player.Player

/**
 * Created by Aedan Smith.
 */

class BeginTurnState(val player: Player) : GameState {
    override fun apply(game: Game): GameState {
        player.playedEnergyForTurn = false
        player.mana.current = player.mana.amount
        player.draw()
        game.resolveStack()
        return game.doTurnStates[game.indexOf(player)]
    }
}
