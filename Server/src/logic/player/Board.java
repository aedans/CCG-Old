package logic.player;

import logic.cards.Permanent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aedan Smith.
 */

public interface Board {
    void add(Permanent permanent);

    String remove(int n);

    List<Permanent> getBoard();

    default void applyStateBasedActions(){
        ArrayList<Permanent> toRemove = new ArrayList<>();
        for (Permanent permanent : getBoard()) {
            if (permanent.health <= 0) {
                toRemove.add(permanent);
            }
        }
        for (Permanent permanent : toRemove) {
            remove(getBoard().indexOf(permanent));
        }
    }
}
