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
            override fun canCast(caster: Player, game: Game): Boolean {
                if (caster.mana.current < 3 || caster.mana.u < 1){
                    return false
                }
                return true
            }

            override fun onCast(caster: Player, game: Game) {
                caster.draw()
                caster.draw()
                caster.mana.current -= 3
            }
        })
        cards.put("C_Damager", object : CardData {
            override fun canCast(caster: Player, game: Game): Boolean {
                if (caster.mana.current < 2){
                    return false
                }
                return true
            }

            override fun onCast(caster: Player, game: Game) {
                caster.board.add(object : Permanent("C_Damager", 2) {

                })
                caster.mana.current -= 2
            }
        })
        cards.put("R_Removal_1", object : CardData {
            override fun canCast(caster: Player, game: Game): Boolean {
                if (caster.mana.r < 1 || caster.mana.amount < 2)
                    return false
                for (player in game.players) {
                    if (!player.board.board.isEmpty()) {
                        return true
                    }
                }
                return false
            }

            override fun onCast(caster: Player, game: Game) {
                val targets = ArrayList<String>()
                val players = game.players
                for (i in players.indices) {
                    for (j in 0..players[i].board.board.size - 1) {
                        targets.add(commands.getString("BOARD") + i + ":" + j)
                    }
                }
                game.stack.push(object : Action {
                    override fun apply(game: Game) {
                        caster.requestTarget(targets).get(game).health -= 2
                    }
                })
                caster.mana.current -= 1
            }
        })
        cards.put("UR_Energy", object : CardData {
            override fun canCast(caster: Player, game: Game): Boolean = !caster.playedEnergyForTurn

            override fun onCast(caster: Player, game: Game) {
                caster.mana.u++
                caster.mana.r++
                caster.mana.amount++
                caster.playedEnergyForTurn = true
            }
        })
    }

    operator fun get(string: String): CardData {
        return cards[string]!!
    }
}
