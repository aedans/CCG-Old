package game.ingame.card;

import engine.components.Component;
import engine.entities.predicates.IsClicked;
import engine.game.GameObject;
import engine.input.MouseButton;
import engine.renderer.resources.TexturedModel;
import engine.renderer.resources.Textures;
import engine.sprites.Sprite;
import game.ingame.Clickable;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * Created by Aedan Smith.
 */

public class CardEntity extends GameObject implements Clickable {
    private static final float FRAME_WIDTH = 114, FRAME_HEIGHT = 256;
    private Component<CardEntity>[] components;
    private Sprite sprite;
    private IsClicked<CardEntity> isClicked = new IsClicked<>(MouseButton.LEFT);

    @SafeVarargs
    public CardEntity(float x, float y, float size, String id, Component<CardEntity>... components) {
        super(x, y, size * FRAME_WIDTH, size * FRAME_HEIGHT);
        this.components = components;
        this.sprite = new Sprite(
                x, y,
                TexturedModel.getTexturedModel(
                        size * FRAME_WIDTH,
                        size * FRAME_HEIGHT,
                        lazyTexture(id)
                )
        );
    }

    @Override
    public void update() {
        for (Component<CardEntity> component : components) {
            component.apply(this);
        }
    }

    @Override
    public void render() {
        sprite.render();
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        sprite.setPosition(x, y);
    }

    @Override
    public void translate(float x, float y) {
        super.translate(x, y);
        sprite.translate(x, y);
    }

    @Override
    public void scale(float xScale, float yScale) {
        super.scale(xScale, yScale);
        sprite.scale(xScale, yScale);
    }

    @Override
    public void setScale(float xScale, float yScale) {
        super.setScale(xScale, yScale);
        sprite.setScale(xScale, yScale);
    }

    @Override
    public float getWidth() {
        return sprite.getWidth();
    }

    @Override
    public float getHeight() {
        return sprite.getHeight();
    }

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public boolean isClicked() {
        return isClicked.test(this);
    }

    private static int lazyTexture(String id) {
        try {
            id = id + ".bmp";
            try {
                return Textures.loadTexture(id, ImageIO.read(new File("assets/imgs/cards/" + id)));
            } catch (Exception e){
                return Textures.getTexture(id);
            }
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}