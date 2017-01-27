package logic.game;

import logic.player.Player;

/**
 * Created by Aedan Smith.
 */

public interface GameAction {
    void apply(Game game, Player current);
}
