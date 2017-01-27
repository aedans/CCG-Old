package server;

/**
 * Created by Aedan Smith.
 */

public final class Actions {
    public static Action get(String str) {
        switch (str.charAt(0)){
            case 'd':
                return inGameGameState -> inGameGameState.getHand().add(str.substring(1));
            case 'D':
                return inGameGameState -> inGameGameState.getHand().remove(str.charAt(1)-33);
            case 'b':
                return inGameGameState -> inGameGameState.getBoard().add(str.substring(1));
            case 'B':
                return inGameGameState -> inGameGameState.getBoard().remove(str.charAt(1)-33);
            case 'a':
                return inGameGameState -> new Thread(() ->
                        Server.write(inGameGameState.getAction())).start();
            case 't':
                return inGameGameState -> new Thread(() ->
                        Server.write(inGameGameState.getTarget(str.substring(1)))).start();
            default:
                throw new RuntimeException("Unrecognized action \"" + str.charAt(0) + "\"");
        }
    }
}
