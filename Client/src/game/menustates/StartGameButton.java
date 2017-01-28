package game.menustates;

import engine.entities.Entity;
import engine.entities.predicates.IsClicked;
import engine.game.StateBasedGame;
import engine.input.MouseButton;
import engine.renderer.resources.TexturedModel;
import engine.renderer.resources.Textures;
import engine.sprites.Sprite;
import engine.utils.Updateable;
import game.ingame.InGameGameState;
import org.lwjgl.util.Renderable;

/**
 * Created by Aedan Smith.
 */

public class StartGameButton extends Entity implements Updateable, Renderable {
    private Sprite sprite;
    private IsClicked<StartGameButton> isClicked = new IsClicked<>(MouseButton.LEFT);
    private StateBasedGame container;

    StartGameButton(StateBasedGame container) {
        super(0, 0, .3f, .3f);
        this.sprite = new Sprite(
                0, 0,
                TexturedModel.getTexturedModel(
                        .3f, .3f,
                        Textures.getTexture("start.png")
                )
        );
        this.container = container;
    }

    @Override
    public void update() {
        if (isClicked.test(this)) {
            container.setActiveGameState(InGameGameState.ID);
        }
    }

    @Override
    public void render() {
        sprite.render();
    }
}
