package game.ingame;

import engine.game.GameState;
import game.ingame.board.Board;
import game.ingame.card.CardEntity;
import game.ingame.card.CardMouseoverComponent;
import game.ingame.hand.Hand;
import game.ingame.server.Server;
import javafx.util.Pair;
import org.lwjgl.input.Mouse;
import game.ingame.server.ServerManager;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Aedan Smith.
 */

public class InGameGameState extends GameState {
    public static final int ID = 1;
    private ServerManager serverManager;
    private Hand hand;
    private Board board;
    private EndTurnButton endTurnButton;

    @Override
    public void init() throws Exception {
        this.hand = new Hand();
        this.board = new Board();
        this.endTurnButton = new EndTurnButton();
    }

    @Override
    public void update() throws Exception {
        if (serverManager == null){
            this.serverManager = new ServerManager(this);
            this.serverManager.sendDeck("test.deck");
        }

        serverManager.update();
        board.update();
        hand.update();
        endTurnButton.update();
    }

    @Override
    public void render() throws Exception {
        board.render();
        hand.render();
        endTurnButton.render();
        CardMouseoverComponent.renderMousedOver();
    }

    public Hand getHand() {
        return hand;
    }

    public Board getBoard() {
        return board;
    }

    public String getAction() {
        ArrayList<Pair<Clickable, String>> clickables = new ArrayList<>();
        clickables.add(new Pair<>(endTurnButton, Server.commands.getString("END_TURN")));
        ArrayList<CardEntity> cardEntities = hand.getCardEntities();
        for (int i = 0; i < cardEntities.size(); i++) {
            clickables.add(new Pair<>(
                    cardEntities.get(i),
                    Server.commands.getString("DO_ACTION") + Server.commands.getString("HAND") + i)
            );
        }
        while (true){
            for (Pair<Clickable, String> clickable : clickables) {
                if (Mouse.isCreated() && clickable.getKey().isClicked()){
                    return clickable.getValue();
                }
            }
        }
    }

    public String getTarget(String s) {
        ArrayList<String> targets = new ArrayList<>();
        Collections.addAll(targets, s.split(Server.commands.getString("TARGET_SEPARATOR")));
        ArrayList<Clickable> clickables = new ArrayList<>();
        for (String target : targets) {
            if (target.startsWith(Server.commands.getString("BOARD") + "0:")) {
                clickables.add(board.get(Integer.parseInt(target.substring((Server.commands.getString("BOARD") + "0:").length()))));
            }
        }
        while (true){
            for (int i = 0; i < clickables.size(); i++) {
                if (clickables.get(i).isClicked()) {
                    return targets.get(i);
                }
            }
        }
    }
}
