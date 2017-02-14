package logic.game

import logic.action.Action
import logic.cards.CardManager
import logic.player.Player
import logic.player.PlayerAction
import logic.player.externalplayer.commands
import java.util.*

/**
 * Created by Aedan Smith.
 */

abstract class Game(var players: MutableList<Player>) : Runnable {
    var stack = Stack<Action>()

    override fun run() {
        onBegin()
        val turns = PlayerIterator(players)
        while (!isOver) {
            val current = turns.next()
            onUpkeep(current)
            resolveStack()
            doTurn(current)
            resolveStack()
            onEndStep(current)
            resolveStack()
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

    protected abstract fun onBegin()

    protected abstract fun onUpkeep(current: Player)

    protected abstract fun onEndStep(current: Player)

    private val isOver = false

    private fun doTurn(current: Player) {
        while (true) {
            resolveStack()
            val playerAction = current.nextAction()
            if (playerAction.actionType == PlayerAction.ActionType.END_TURN) {
                return
            } else if (playerAction.actionType == PlayerAction.ActionType.DO_ACTION) {
                get(playerAction.value).apply(this, current)
            }
        }
    }

    private fun get(string: String): GameAction {
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
