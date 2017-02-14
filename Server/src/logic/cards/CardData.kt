package logic.cards

import logic.game.Game
import logic.player.Player

/**
 * Created by Aedan Smith.
 */

interface CardData {
    fun canCast(caster: Player, game: Game): Boolean
    fun onCast(caster: Player, game: Game)
}
