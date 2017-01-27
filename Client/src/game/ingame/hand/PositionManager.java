package game.ingame.hand;

import game.ingame.card.CardEntity;

import java.util.List;

/**
 * Created by Aedan Smith.
 */

public class PositionManager {
    public static void apply(List<CardEntity> cards, float y) {
        float[] dist = getDistribution(cards.size());
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).setPosition(
                    dist[i],
                    y
            );
        }
    }

    private static float[] getDistribution(int size){
        if (size == 0)
            return new float[]{};
        float d = 1.5f / (float) size;
        float[] floats = new float[size];
        for (float i = 0, f = -.75f; i < floats.length; i++, f+=d) {
            floats[(int) i] = f + d/2;
        }
        return floats;
    }
}
