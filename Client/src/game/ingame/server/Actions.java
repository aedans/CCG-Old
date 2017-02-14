package game.ingame.server;

/**
 * Created by Aedan Smith.
 */

public final class Actions {
    private static final String REQUEST_ACTION = Server.commands.getString("REQUEST_ACTION");
    private static final String REQUEST_TARGET = Server.commands.getString("REQUEST_TARGET");
    private static final String DRAW_CARD = Server.commands.getString("DRAW_CARD");
    private static final String DISCARD_CARD = Server.commands.getString("DISCARD_CARD");
    private static final String PLAY_CARD = Server.commands.getString("PLAY_CARD");
    private static final String KILL_CARD = Server.commands.getString("KILL_CARD");

    public static Action get(String str) {
        if (str.startsWith(REQUEST_ACTION)) {
            return inGameGameState -> new Thread(() -> Server.write(inGameGameState.getAction())).start();
        }
        if (str.startsWith(REQUEST_TARGET)) {
            return inGameGameState -> new Thread(() -> Server.write(inGameGameState.getTarget(str.substring(REQUEST_TARGET.length())))).start();
        }
        if (str.startsWith(DRAW_CARD)) {
            return inGameGameState -> inGameGameState.getHand().add(str.substring(DRAW_CARD.length()));
        }
        if (str.startsWith(DISCARD_CARD)) {
            return inGameGameState -> inGameGameState.getHand().remove(Integer.parseInt(str.substring(DISCARD_CARD.length())));
        }
        if (str.startsWith(PLAY_CARD)) {
            return inGameGameState -> inGameGameState.getBoard().add(str.substring(PLAY_CARD.length()));
        }
        if (str.startsWith(KILL_CARD)) {
            return inGameGameState -> inGameGameState.getBoard().remove(Integer.parseInt(str.substring(KILL_CARD.length())));
        }

        throw new RuntimeException("Unrecognized action \"" + str + "\"");
    }
}
