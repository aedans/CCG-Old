package logic.game

import logic.player.Player
import java.util.function.Consumer

/**
 * Created by Aedan Smith.
 */

class StandardGame(players: MutableList<Player>) : Game(players) {
    override fun onBegin() {
        for (i in 0..0) {
            players.forEach(Consumer<Player> { it.draw() })
        }
    }

    override fun onUpkeep(current: Player) {
        current.draw()
    }

    override fun onEndStep(current: Player) {

    }
}
