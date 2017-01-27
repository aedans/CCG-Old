package game.menustates;

import engine.entities.predicates.IsClicked;
import engine.game.GameObject;
import engine.game.GameState;
import engine.input.MouseButton;
import engine.renderer.resources.TexturedModel;
import engine.renderer.resources.Textures;
import engine.sprites.Sprite;
import game.ingame.InGameGameState;

import java.util.LinkedList;

/**
 * Created by Aedan Smith.
 */

public class MainMenuGameState extends GameState {
    public static final int ID = 0;

    private LinkedList<GameObject> entities = new LinkedList<>();

    @Override
    public void init() throws Exception {
        entities.add(new GameObject(0, 0, .3f, .3f) {
            private IsClicked isClicked = new IsClicked(MouseButton.LEFT);
            private Sprite sprite = new Sprite(0, 0, TexturedModel.getTexturedModel(
                    .3f,
                    .3f,
                    Textures.getTexture("start.png")
            ));

            @Override
            public void update() {
                if (isClicked.test(this)){
                    container.setActiveGameState(InGameGameState.ID);
                }
            }

            @Override
            public void render() {
                sprite.render();
            }
        });
    }

    @Override
    public void update() throws Exception {
        entities.forEach(GameObject::update);
    }

    @Override
    public void render() throws Exception {
        entities.forEach(GameObject::render);
    }
}
