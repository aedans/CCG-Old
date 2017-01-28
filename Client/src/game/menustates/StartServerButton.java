package game.menustates;

import engine.entities.Entity;
import engine.entities.predicates.IsClicked;
import engine.input.MouseButton;
import engine.renderer.resources.TexturedModel;
import engine.renderer.resources.Textures;
import engine.sprites.Sprite;
import engine.utils.Logger;
import engine.utils.Updateable;
import org.lwjgl.util.Renderable;
import server.Server;

import java.io.IOException;

/**
 * Created by Aedan Smith.
 */

public class StartServerButton extends Entity implements Renderable, Updateable {
    private Sprite sprite;
    private IsClicked<StartServerButton> isClicked = new IsClicked<>(MouseButton.LEFT);

    StartServerButton(){
        super(0, -.5f, .2f, .2f);
        this.sprite = new Sprite(
                0, -.5f,
                TexturedModel.getTexturedModel(
                        .2f,
                        .2f,
                        Textures.getTexture("start.png")
                )
        );
    }

    @Override
    public void update() {
        if (isClicked.test(this)){
            try {
                Process process = Runtime.getRuntime().exec("java -jar Server.jar 345 1");
                Logger.log("Began server");
                process.waitFor();
                Logger.log("Server initialized");
                Server.connect("localhost", 345);
                Logger.log("Connected to server");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void render() {
        sprite.render();
    }
}
