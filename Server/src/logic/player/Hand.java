package logic.player;

import java.util.List;

/**
 * Created by Aedan Smith.
 */

public interface Hand {
    void add(String s);

    String remove(int n);

    String get(int n);

    List<String> getHand();
}
