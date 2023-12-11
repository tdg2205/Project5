import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * MarketplaceServer.java
 * <p>
 * This is a MarketplaceServer.java class which is the actual server and contain server sockets.
 *
 * @author Krish Sharma, lab sec 30
 * @version 11 November, 2023
 */
public class MarketplaceServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is listening on port 12345...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                Thread clientHandler = new Thread(new ClientHandler(clientSocket));
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
