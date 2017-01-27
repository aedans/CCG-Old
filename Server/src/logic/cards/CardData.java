package logic.cards;

import logic.game.Game;
import logic.player.Player;

/**
 * Created by Aedan Smith.
 */

public interface CardData {
    boolean canCast(Player current, Game game);
    void onCast(Player current, Game game);
}
