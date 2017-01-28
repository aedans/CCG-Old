import engine.utils.Logger;
import game.Game;
import server.Server;

/**
 * Created by Aedan Smith.
 */

public class Main {
    public static void main(String[] args) {
        try {
            new Game().run();
            Server.disconnect();
        } catch (Exception e){
            Logger.log(e);
        }
        Logger.log("Program finished");
        System.exit(0);
    }
}
