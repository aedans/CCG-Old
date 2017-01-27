package logic.player;

import java.util.ArrayList;

/**
 * Created by Aedan Smith.
 */

public abstract class Player {
    public Deck deck;
    public Hand hand;
    public Board board;

    public Player(Deck deck, Hand hand, Board board){
        this.deck = deck;
        this.hand = hand;
        this.board = board;
    }

    public void draw(){
        if (!deck.isEmpty()) {
            hand.add(deck.pop());
        }
    }

    public abstract PlayerAction nextAction();

    public abstract String requestTarget(ArrayList<String> targets);
}
