package logic.game;

import logic.player.Player;

import java.util.List;

/**
 * Created by Aedan Smith.
 */

public class StandardGame extends Game {
    public StandardGame(List<Player> players){
        super(players);
    }

    @Override
    public void onBegin() {
        for (int i = 0; i < 1; i++) {
            players.forEach(Player::draw);
        }
    }

    @Override
    public void onUpkeep(Player current) {
        current.draw();
    }

    @Override
    public void onEndStep(Player current) {

    }
}
