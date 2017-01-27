package game;

import engine.game.StateBasedGame;
import engine.renderer.resources.Textures;
import game.ingame.InGameGameState;
import game.menustates.MainMenuGameState;
import org.lwjgl.LWJGLException;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * Created by Aedan Smith.
 */

public class Game extends StateBasedGame {
    public Game() throws Exception {
        super(
                1280, 720, false, "CCG",
                new MainMenuGameState(),
                new InGameGameState()
        );
    }

    @Override
    protected void init() throws LWJGLException {
        super.init();
        try {
            //noinspection ConstantConditions
            for (File f : new File("assets/imgs").listFiles()){
                Textures.loadTexture(f.getName(), ImageIO.read(f));
            }
        } catch (Exception e) {
            throw new LWJGLException(e);
        }
    }
}
