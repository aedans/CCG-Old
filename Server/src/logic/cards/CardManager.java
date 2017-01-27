package logic.cards;

import logic.game.Game;
import logic.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aedan Smith.
 */

public final class CardManager {
    private static final HashMap<String, CardData> cards = new HashMap<>();

    static {
        cards.put("U_Support", new CardData() {
            @Override
            public boolean canCast(Player current, Game game) {
                return true;
            }

            @Override
            public void onCast(Player current, Game game) {
                current.draw();
                current.draw();
            }
        });
        cards.put("C_Damager", new CardData() {
            @Override
            public boolean canCast(Player current, Game game) {
                return true;
            }

            @Override
            public void onCast(Player current, Game game) {
                current.board.add(new Permanent("C_Damager", 2){

                });
            }
        });
        cards.put("R_Removal", new CardData() {
            @Override
            public boolean canCast(Player current, Game game) {
                for (Player player : game.players) {
                   if (!player.board.getBoard().isEmpty()){
                       return true;
                   }
                }
                return false;
            }

            @Override
            public void onCast(Player current, Game game) {
                ArrayList<String> targets = new ArrayList<>();
                List<Player> players = game.players;
                for (int i = 0; i < players.size(); i++) {
                    for (int j = 0; j < players.get(i).board.getBoard().size(); j++) {
                        targets.add("b" + (char) (i+33) + (char) (j + 33));
                    }
                }
                game.stack.push(game.getActionFactory().getDamageAction(current.requestTarget(targets), 2));
            }
        });
    }

    public static CardData get(String string){
        return cards.get(string);
    }
}
