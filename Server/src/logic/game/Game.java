package logic.game;

import logic.action.Action;
import logic.action.ActionFactory;
import logic.cards.CardData;
import logic.cards.CardManager;
import logic.player.Player;
import logic.player.PlayerAction;

import java.util.List;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public abstract class Game implements Runnable {
    private ActionFactory actionFactory;
    public Stack<Action> stack = new Stack<>();
    public List<Player> players;

    public Game(List<Player> players){
        this.players = players;
        this.actionFactory = new ActionFactory();
    }

    @Override
    public void run() {
        onBegin();
        PlayerIterator turns = new PlayerIterator(players);
        while (!isOver()){
            Player current = turns.next();
            onUpkeep(current);
            resolveStack();
            doTurn(current);
            resolveStack();
            onEndStep(current);
            resolveStack();
        }
    }

    public void resolveStack(){
        while (!stack.isEmpty()){
            stack.pop().apply(this);
            applyStateBasedActions();
        }
        applyStateBasedActions();
    }

    public void applyStateBasedActions(){
        for (Player player : players) {
            player.board.applyStateBasedActions();
        }
    }

    protected abstract void onBegin();

    protected abstract void onUpkeep(Player current);

    protected abstract void onEndStep(Player current);

    private boolean isOver() {
        return false;
    }

    private void doTurn(Player current){
        while (true) {
            resolveStack();
            PlayerAction playerAction = current.nextAction();
            if (playerAction.getActionType() == PlayerAction.ActionType.END_TURN)
                return;
            else if (playerAction.getActionType() == PlayerAction.ActionType.DO_ACTION)
                get(playerAction.getValue()).apply(this, current);
        }
    }

    private GameAction get(String string){
        if (string.charAt(0) == 'h'){
            return new GameAction() {
                @Override
                public void apply(Game game, Player current) {
                    int index = string.charAt(1)-33;
                    CardData cardData = CardManager.get(current.hand.get(index));
                    if (cardData.canCast(current, game)) {
                        current.hand.remove(index);
                        cardData.onCast(current, game);
                    }
                }
            };
        }
        throw new RuntimeException("Could not find action \"" + string + "\"");
    }

    public Object getTarget(String s) {
        switch (s.charAt(0)){
            case 'b':
                return players.get(s.charAt(1)-33).board.getBoard().get(s.charAt(1)-33);
            default:
                throw new RuntimeException("Could not find target \"" + s + "\"");
        }
    }

    public ActionFactory getActionFactory() {
        return actionFactory;
    }
}
