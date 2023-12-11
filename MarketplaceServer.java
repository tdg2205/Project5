import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Store.java
 * <p>
 * This is a Store.java class which contains all the fields, getters and setters method along with increasing purchase count and updating quanity methods.
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
