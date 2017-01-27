package server;

import engine.utils.Logger;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Aedan Smith.
 */

public final class Server {
    private static Socket socket;
    private static PrintStream out;
    public static Scanner in;

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
