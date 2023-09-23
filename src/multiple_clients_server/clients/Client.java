package multiple_clients_server.clients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static String username;
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8080);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            username = scanner.nextLine();
            dataOutputStream.writeUTF(username + " has joined!");

            while (true) {
                System.out.print(username + ": ");
                String message = scanner.nextLine();
                dataOutputStream.writeUTF(username+": "+message);
                if(message.equalsIgnoreCase("_stop"))
                    break;
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
