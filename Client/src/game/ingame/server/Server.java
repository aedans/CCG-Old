package game.ingame.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import engine.utils.Logger;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Aedan Smith.
 */

public final class Server {
    private static Socket socket;
    private static PrintStream out;
    public static Scanner in;
    public static JSONObject commands;

    static {
        try {
            String s = "";
            for (String s1 : Files.readAllLines(Paths.get("assets/server.json"))) {
                s += s1 + "\n";
            }
            commands = JSON.parseObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void connect(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintStream(socket.getOutputStream());
        in = new Scanner(socket.getInputStream());
    }

    public static void write(String s){
        out.println(s);
        Logger.log("Sent message \"" + s + "\"");
    }

    public static void disconnect() throws IOException {
        socket.close();
    }
}
