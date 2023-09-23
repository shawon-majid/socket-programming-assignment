package multiple_clients_server.clients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket clientSocket;

    DataInputStream dataInputStream = null;
    DataOutputStream dataOutputStream = null;
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try{
            System.out.println(clientSocket+" connected");
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            String message;
            while (true) {
                message = dataInputStream.readUTF();
                System.out.println(message);
                if(message.equalsIgnoreCase("_stop"))
                    break;
            }
            clientSocket.close();

        }catch (Exception e){
            System.out.println(e);
        }

    }
}
