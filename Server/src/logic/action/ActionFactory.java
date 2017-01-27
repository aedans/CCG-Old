package logic.action;

import logic.cards.Permanent;
import logic.game.Game;

/**
 * Created by Aedan Smith.
 */

public class ActionFactory {
    public Action getDamageAction(String target, int damage) {
        return new Action() {
            @Override
            public void apply(Game game) {
                ((Permanent) game.getTarget(target)).health -= damage;
            }
        };
    }
}
