package connection;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Aedan Smith.
 */

@SuppressWarnings("Duplicates")
public class Connection {
    private Socket socket;
    private int port;
    public Scanner in;
    public PrintStream out;

    public Connection(int port){
        this.port = port;
    }

    public void connect() throws IOException {
        System.out.println("Connecting to " + port);
        new Thread(() -> {
            try {
                socket = new ServerSocket(port).accept();
                in = new Scanner(socket.getInputStream());
                out = new PrintStream(socket.getOutputStream());
                System.out.println("Connected to " + port);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }).start();
    }

    public void waitUntilConnected() throws InterruptedException {
        //noinspection StatementWithEmptyBody
        while (socket == null){
            Thread.sleep(500);
            System.out.print(".");
        }
    }

    public void close() throws IOException {
        socket.close();
    }

    public static Connection[] open(int port, int num) throws InterruptedException, IOException {
        Connection[] connections = new Connection[num];
        for (int i = 0; i < connections.length; i++) {
            connections[i] = new Connection(port + i);
        }
        for (Connection connection : connections) {
            connection.connect();
        }
        for (Connection connection : connections) {
            connection.waitUntilConnected();
        }
        System.out.println("\nEstablished connections");
        return connections;
    }
}
