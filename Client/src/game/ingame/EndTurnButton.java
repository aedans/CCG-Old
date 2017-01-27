package game.ingame;

import engine.entities.Entity;
import engine.entities.predicates.IsClicked;
import engine.input.MouseButton;
import engine.renderer.resources.TexturedModel;
import engine.renderer.resources.Textures;
import engine.sprites.Sprite;
import engine.utils.Updateable;
import org.lwjgl.util.Renderable;

/**
 * Created by Aedan Smith.
 */

public class EndTurnButton extends Entity implements Renderable, Updateable, Clickable {
    private Sprite sprite = new Sprite(
            .8f, 0,
            TexturedModel.getTexturedModel(
                    .1f,
                    .1f,
                    Textures.getTexture("start.png")
            )
    );
    private IsClicked<EndTurnButton> isClicked = new IsClicked<>(MouseButton.LEFT);

    public EndTurnButton() {
        super(.8f, 0, .1f, .1f);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        sprite.render();
    }

    @Override
    public boolean isClicked() {
        return isClicked.test(this);
    }
}
