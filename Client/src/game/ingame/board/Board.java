package game.ingame.board;

import engine.utils.Updateable;
import game.ingame.card.CardEntity;
import game.ingame.card.CardMouseoverComponent;
import game.ingame.hand.PositionManager;
import org.lwjgl.util.Renderable;

import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 */

public class Board implements Renderable, Updateable {
    private ArrayList<String> cards = new ArrayList<>();
    private ArrayList<CardEntity> cardEntities = new ArrayList<>();

    public void add(String id) {
        cards.add(id);
        cardEntities.add(new CardEntity(
                0, 0, .0023f, id, new CardMouseoverComponent()
        ));
    }

    public void remove(int n){
        cards.remove(n);
        cardEntities.remove(n);
    }

    public CardEntity get(int i) {
        return cardEntities.get(i);
    }

    @Override
    public void update() {
        PositionManager.apply(cardEntities, -.3f);
        cardEntities.forEach(CardEntity::update);
    }

    @Override
    public void render() {
        cardEntities.forEach(CardEntity::render);
    }
}
