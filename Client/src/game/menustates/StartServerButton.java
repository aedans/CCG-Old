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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        if (isClicked.test(this)) {
            try {
                Process process = Runtime.getRuntime().exec("java -jar Server.jar 9000 2");
                new Thread(() -> new BufferedReader(new InputStreamReader(process.getInputStream())).lines()
                        .forEach(s -> Logger.log("(Server) " + s))).start();
                new Thread(() -> new BufferedReader(new InputStreamReader(process.getErrorStream())).lines()
                        .forEach(s -> Logger.log("(Server) " + s))).start();
                Logger.log("Server initialized");
                Server.connect("localhost", 9000);
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
