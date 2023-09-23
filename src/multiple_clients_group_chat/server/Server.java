    package multiple_clients_group_chat.server;

    import multiple_clients_group_chat.clients.ClientHandler;

    import java.io.IOException;
    import java.net.ServerSocket;
    import java.net.Socket;
    import java.util.Scanner;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;

    public class Server {

        // Server socket that accepts client and handles data flow.
        private ServerSocket serverSocket;
        private ExecutorService executor;

        public Server(ServerSocket serverSocket, int numberOfParticipants) {
            this.serverSocket = serverSocket;
            executor = Executors.newFixedThreadPool(numberOfParticipants);
        }


        public void starServer() {
            try {
                while (!serverSocket.isClosed()) {
                    // New connected client socket.
                    Socket socket = serverSocket.accept();
                    System.out.println("A new client has been connected!");
                    executor.execute(new ClientHandler(socket));
                }
            } catch (IOException e) {
                e.printStackTrace();
                closeServerSocket();
            }
        }

        public void closeServerSocket() {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server started listening on port: 8888");
            Scanner input = new Scanner(System.in);

            System.out.print("Set Number of participants: ");
            int numberOfParticipants = input.nextInt();

            Server server = new Server(serverSocket, numberOfParticipants);
            server.starServer();
        }

    }