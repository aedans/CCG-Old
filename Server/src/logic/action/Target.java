package logic.action;

import logic.game.Game;

/**
 * Created by Aedan Smith.
 */

public interface Target {
    Object get(Game game);

    static Target fromString(String s){
        switch (s.charAt(0)){
            case 'b':
                return game -> game.players.get(s.charAt(1)-33).board.getBoard().get(s.charAt(1)-33);
            default:
                throw new RuntimeException("Could not find target \"" + s + "\"");
        }
    }
}
