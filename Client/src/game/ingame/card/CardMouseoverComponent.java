package game.ingame.card;

import engine.components.PredicateComponent;
import engine.entities.predicates.IsHovered;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by Aedan Smith.
 */

public class CardMouseoverComponent extends PredicateComponent<CardEntity> {
    public static CardEntity mousedOver = null;

    public CardMouseoverComponent() {
        super(cardObject -> {
            if (IsHovered.testEntity(cardObject)){
                mousedOver = cardObject;
            } else {
                return false;
            }
            return true;
        });
    }


    public static void renderMousedOver() {
        if (mousedOver != null) {
            mousedOver.setScale(2f, 2f);
            mousedOver.getSprite().setTransformationMatrix(
                    mousedOver.getSprite().getTransformationMatrix().translate(
                            new Vector2f(0, mousedOver.getY() / -4f)
                    )
            );
            mousedOver.render();
            mousedOver.setScale(1, 1);
            mousedOver = null;
        }
    }

    @Override
    protected void whenTrue(CardEntity cardEntity) {

    }

    @Override
    protected void whenFalse(CardEntity cardEntity) {

    }
}
