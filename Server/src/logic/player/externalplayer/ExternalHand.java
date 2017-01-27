package logic.player.externalplayer;

import connection.ClientAction;
import connection.Connection;
import logic.player.Hand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aedan Smith.
 */

public class ExternalHand implements Hand {
    private ArrayList<String> cards = new ArrayList<>();
    private Connection connection;

    public ExternalHand(Connection connection){
        this.connection = connection;
    }

    @Override
    public void add(String s) {
        connection.out.println(ClientAction.DRAW_CARD + s);
        cards.add(s);
    }

    @Override
    public String remove(int n) {
        connection.out.println(ClientAction.DISCARD_CARD + (char) (n+33));
        return cards.remove(n);
    }

    @Override
    public String get(int n) {
        return cards.get(n);
    }

    @Override
    public List<String> getHand() {
        return cards;
    }
}
