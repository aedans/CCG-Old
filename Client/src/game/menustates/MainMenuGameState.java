package game.menustates;

import engine.game.GameState;

/**
 * Created by Aedan Smith.
 */

public class MainMenuGameState extends GameState {
    public static final int ID = 0;

    private StartServerButton startServerButton;
    private StartGameButton startGameButton;

    @Override
    public void init() throws Exception {
        this.startGameButton = new StartGameButton(container);
        this.startServerButton = new StartServerButton();
    }

    @Override
    public void update() throws Exception {
        startGameButton.update();
        startServerButton.update();
    }

    @Override
    public void render() throws Exception {
        startGameButton.render();
        startServerButton.render();
    }
}
