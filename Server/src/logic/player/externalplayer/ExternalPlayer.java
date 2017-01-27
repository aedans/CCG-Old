package logic.player.externalplayer;

import connection.Connection;
import logic.player.Deck;
import logic.player.Player;
import logic.player.PlayerAction;

import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 */

public class ExternalPlayer extends Player {
    private Connection connection;

    public ExternalPlayer(Connection connection){
        super(loadDeck(connection), new ExternalHand(connection), new ExternalBoard(connection));
        this.connection = connection;
    }

    public PlayerAction nextAction(){
        connection.out.println("a");
        String s = connection.in.next();
        switch (s.charAt(0)){
            case 'a':
                return new PlayerAction(PlayerAction.ActionType.DO_ACTION, s.substring(1));
            case 'e':
                return new PlayerAction(PlayerAction.ActionType.END_TURN, "");
            default:
                throw new RuntimeException("Could not find action \"" + s + "\"");
        }
    }

    @Override
    public String requestTarget(ArrayList<String> targets) {
        String string = "t";
        for (String target : targets) {
            string += target;
        }
        connection.out.println(string);
        return connection.in.next();
    }

    private static Deck loadDeck(Connection connection) {
        Deck deck = new Deck();
        for (String s : connection.in.next().split(",")){
            deck.add(0, s);
        }
        return deck;
    }
}
