import connection.Connection;
import logic.player.externalplayer.ExternalPlayer;
import logic.player.Player;
import logic.game.StandardGame;

import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        Connection[] connections = Connection.open(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        ArrayList<Player> players = new ArrayList<>();
        for (Connection connection : connections) {
            players.add(new ExternalPlayer(connection));
        }
        StandardGame standardGame = new StandardGame(players);
        standardGame.run();

        for (Connection connection : connections) {
            connection.close();
        }
    }
}
