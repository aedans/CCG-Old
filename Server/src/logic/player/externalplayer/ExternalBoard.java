package logic.player.externalplayer;

import connection.ClientAction;
import connection.Connection;
import logic.cards.Permanent;
import logic.player.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aedan Smith.
 */

public class ExternalBoard implements Board {
    private ArrayList<Permanent> permanents = new ArrayList<>();
    private Connection connection;

    public ExternalBoard(Connection connection){
        this.connection = connection;
    }

    @Override
    public void add(Permanent permanent) {
        connection.out.println(ClientAction.PLAY_CARD + permanent.getId());
        permanents.add(permanent);
    }

    @Override
    public String remove(int n) {
        connection.out.println(ClientAction.KILL_CARD + (char) (n+33));
        return permanents.remove(n).getId();
    }

    @Override
    public List<Permanent> getBoard() {
        return permanents;
    }
}
