package logic.cards

import logic.action.Action
import logic.game.Game
import logic.player.Player
import logic.player.externalplayer.commands
import java.util.*

/**
 * Created by Aedan Smith.
 */

object CardManager {
    private val cards = HashMap<String, CardData>()

    init {
        cards.put("U_Support", object : CardData {
            override fun canCast(current: Player, game: Game): Boolean {
                return true
            }

            override fun onCast(current: Player, game: Game) {
                current.draw()
                current.draw()
            }
        })
        cards.put("C_Damager", object : CardData {
            override fun canCast(current: Player, game: Game): Boolean {
                return true
            }

            override fun onCast(current: Player, game: Game) {
                current.board.add(object : Permanent("C_Damager", 2) {

                })
            }
        })
        cards.put("R_Removal_1", object : CardData {
            override fun canCast(current: Player, game: Game): Boolean {
                for (player in game.players) {
                    if (!player.board.board.isEmpty()) {
                        return true
                    }
                }
                return false
            }

            override fun onCast(current: Player, game: Game) {
                val targets = ArrayList<String>()
                val players = game.players
                for (i in players.indices) {
                    for (j in 0..players[i].board.board.size - 1) {
                        targets.add(commands.getString("BOARD") + i + ":" + j)
                    }
                }
                game.stack.push(object : Action {
                    override fun apply(game: Game) {
                        current.requestTarget(targets).get(game).health -= 2
                    }
                })
            }
        })
    }

    operator fun get(string: String): CardData {
        return cards[string]!!
    }
}
