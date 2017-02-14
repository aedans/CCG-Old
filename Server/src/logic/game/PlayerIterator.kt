package logic.game

import logic.player.Player

/**
 * Created by Aedan Smith.
 */

class PlayerIterator(private val players: MutableList<Player>) : Iterator<Player> {
    private var index: Int = 0

    override fun hasNext(): Boolean {
        return !players.isEmpty()
    }

    override fun next(): Player {
        index++
        if (index >= players.size)
            index = 0
        return players[index]
    }

    fun remove(player: Player) {
        val playerIndex = players.indexOf(player)
        if (playerIndex <= index) {
            index--
        }
        players.removeAt(playerIndex)
    }
}
