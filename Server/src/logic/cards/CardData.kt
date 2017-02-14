package logic.cards

import logic.game.Game
import logic.player.Player

/**
 * Created by Aedan Smith.
 */

interface CardData {
    fun canCast(current: Player, game: Game): Boolean
    fun onCast(current: Player, game: Game)
}
