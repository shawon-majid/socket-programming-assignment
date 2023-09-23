package multiple_clients_server.server;
import multiple_clients_server.clients.ClientHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    private static ServerSocket serverSocket;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("listening to port:8080");
            ExecutorService executor = Executors.newFixedThreadPool(10);

            while (!serverSocket.isClosed()){
                Socket clientSocket = serverSocket.accept();
                executor.execute(new ClientHandler(clientSocket));
            }


        }catch (Exception e){
            System.out.println(e);
            try {
                serverSocket.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }

        }
    }

}
