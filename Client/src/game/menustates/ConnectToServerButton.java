package game.menustates;

import engine.entities.Entity;
import engine.entities.predicates.IsClicked;
import engine.input.MouseButton;
import engine.renderer.resources.TexturedModel;
import engine.renderer.resources.Textures;
import engine.sprites.Sprite;
import engine.utils.Logger;
import engine.utils.Updateable;
import game.ingame.server.Server;
import org.lwjgl.util.Renderable;

import java.io.IOException;

/**
 * Created by Aedan Smith.
 */

public class ConnectToServerButton extends Entity implements Renderable, Updateable {
    private Sprite sprite;
    private IsClicked<ConnectToServerButton> isClicked = new IsClicked<>(MouseButton.LEFT);

    ConnectToServerButton(){
        super(0, -.8f, .2f, .2f);
        this.sprite = new Sprite(
                0, -.8f,
                TexturedModel.getTexturedModel(
                        .2f,
                        .2f,
                        Textures.getTexture("start.png")
                )
        );
    }

    @Override
    public void update() {
        if (isClicked.test(this)) {
            try {
                Server.connect("localhost", 9001);
                Logger.log("Connected to server");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void render() {
        sprite.render();
    }
}
