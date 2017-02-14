package logic.game

import logic.action.Action
import logic.cards.CardManager
import logic.game.gamestates.BeginGameState
import logic.game.gamestates.BeginTurnState
import logic.game.gamestates.DoTurnState
import logic.game.gamestates.EndTurnState
import logic.player.Player
import logic.player.externalplayer.commands
import java.util.*

/**
 * Created by Aedan Smith.
 */

class Game(val players: MutableList<Player>) : Runnable {
    var stack = Stack<Action>()
    var beginGameState = BeginGameState()
    val beginTurnStates = ArrayList<BeginTurnState>(players.size)
    val doTurnStates = ArrayList<DoTurnState>(players.size)
    val endTurnStates = ArrayList<EndTurnState>(players.size)

    init {
        players.forEach { beginTurnStates.add(BeginTurnState(it)) }
        players.forEach { doTurnStates.add(DoTurnState(it)) }
        players.forEach { endTurnStates.add(EndTurnState(it)) }
    }
    
    override fun run() {
        var nextState: GameState = beginGameState
        while (true) {
            nextState = nextState.apply(this)
        }
    }

    fun resolveStack() {
        while (!stack.isEmpty()) {
            stack.pop().apply(this)
            applyStateBasedActions()
        }
        applyStateBasedActions()
    }

    fun applyStateBasedActions() {
        for (player in players) {
            player.board.applyStateBasedActions()
        }
    }

    fun indexOf(player: Player) = players.indexOf(player)

    fun get(string: String): GameAction {
        if (string.startsWith(commands.getString("HAND"))) {
            return object : GameAction {
                override fun apply(game: Game, current: Player) {
                    val index = Integer.parseInt(string.substring(commands.getString("HAND").length))
                    val cardData = CardManager[current.hand[index]]
                    if (cardData.canCast(current, game)) {
                        current.hand.remove(index)
                        cardData.onCast(current, game)
                    }
                }
            }
        }
        throw RuntimeException("Could not find action \"" + string + "\"")
    }
}
