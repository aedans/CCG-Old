package game.ingame.server;

import engine.utils.Logger;
import game.ingame.InGameGameState;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

/**
 * Created by Aedan Smith.
 */

public class ServerManager {
    private Stack<Action> actionStack = new Stack<>();
    private InGameGameState inGameGameState;

    public ServerManager(InGameGameState inGameGameState){
        this.inGameGameState = inGameGameState;

        new Thread(() -> Server.in.forEachRemaining(s -> {
            Logger.log("Received message \"" + s + "\"");
            actionStack.add(0, Actions.get(s));
        })).start();
    }

    public void update(){
        while (!actionStack.isEmpty()) {
            actionStack.pop().accept(inGameGameState);
        }
    }

    public void sendDeck(String file) throws IOException {
        String deck = new String(Files.readAllBytes(Paths.get(file)));
        Server.write(deck);
    }
}
