package game.ingame;

import engine.game.GameState;
import game.ingame.board.Board;
import game.ingame.card.CardEntity;
import game.ingame.card.CardMouseoverComponent;
import game.ingame.hand.Hand;
import javafx.util.Pair;
import org.lwjgl.input.Mouse;
import server.ServerManager;

import java.util.ArrayList;

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
        clickables.add(new Pair<>(endTurnButton, "e"));
        ArrayList<CardEntity> cardEntities = hand.getCardEntities();
        for (int i = 0; i < cardEntities.size(); i++) {
            clickables.add(new Pair<>(cardEntities.get(i), "ah" + (char)(i+33)));
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
        for (int i = 0; i < s.length(); i+=3) {
            targets.add(Character.toString(s.charAt(i)) + s.charAt(i+1) + s.charAt(i+2));
        }
        ArrayList<Clickable> clickables = new ArrayList<>();
        for (String target : targets) {
            switch (target.charAt(0)){
                case 'b':
                    clickables.add(board.get(target.charAt(2)-33));
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
