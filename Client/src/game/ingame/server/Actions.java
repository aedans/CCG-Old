package game.ingame.server;

import engine.utils.Logger;

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
    private static final String SET_MANA_U = Server.commands.getString("SET_MANA_U");
    private static final String SET_MANA_B = Server.commands.getString("SET_MANA_B");
    private static final String SET_MANA_R = Server.commands.getString("SET_MANA_R");
    private static final String SET_MANA_G = Server.commands.getString("SET_MANA_G");
    private static final String SET_MANA_AMOUNT = Server.commands.getString("SET_MANA_AMOUNT");
    private static final String SET_MANA_CURRENT = Server.commands.getString("SET_MANA_CURRENT");

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
        if (str.startsWith(SET_MANA_U)) {
            Logger.log("U mana: " + str.substring(SET_MANA_U.length()));
            return inGameGameState -> {}; // TODO implement
        }
        if (str.startsWith(SET_MANA_B)) {
            Logger.log("B mana: " + str.substring(SET_MANA_B.length()));
            return inGameGameState -> {}; // TODO implement
        }
        if (str.startsWith(SET_MANA_R)) {
            Logger.log("R mana: " + str.substring(SET_MANA_R.length()));
            return inGameGameState -> {}; // TODO implement
        }
        if (str.startsWith(SET_MANA_G)) {
            Logger.log("G mana: " + str.substring(SET_MANA_G.length()));
            return inGameGameState -> {}; // TODO implement
        }
        if (str.startsWith(SET_MANA_AMOUNT)) {
            Logger.log("Mana amount: " + str.substring(SET_MANA_AMOUNT.length()));
            return inGameGameState -> {}; // TODO implement
        }
        if (str.startsWith(SET_MANA_CURRENT)) {
            Logger.log("Current mana: " + str.substring(SET_MANA_CURRENT.length()));
            return inGameGameState -> {}; // TODO implement
        }

        throw new RuntimeException("Unrecognized action \"" + str + "\"");
    }
}
