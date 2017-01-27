package server;

import game.ingame.InGameGameState;

import java.util.function.Consumer;

/**
 * Created by Aedan Smith.
 */

public interface Action extends Consumer<InGameGameState> {
}
