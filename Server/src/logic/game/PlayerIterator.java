package logic.game;

import logic.player.Player;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Aedan Smith.
 */

public class PlayerIterator implements Iterator<Player> {
    private List<Player> players;
    private int index;

    public PlayerIterator(List<Player> players){
        this.players = players;
    }

    @Override
    public boolean hasNext() {
        return !players.isEmpty();
    }

    @Override
    public Player next() {
        index++;
        if (index >= players.size())
            index = 0;
        return players.get(index);
    }

    public void remove(Player player){
        int playerIndex = players.indexOf(player);
        if (playerIndex <= index){
            index--;
        }
        players.remove(playerIndex);
    }
}
