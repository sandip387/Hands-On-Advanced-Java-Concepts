package $06_networking_and_javafx;

import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        final int PORT = 8080;
        ServerSocket serverSocket = null;

        try {
            // Create a ServerSocket to listen on a specific port.
            serverSocket = new ServerSocket(PORT);
            System.out.println("File Server started. Listening on port " + PORT + "...");

            // Enter an infinite loop to wait for client connections. A real server runs
            // forever, handling on client after another.
            while (true) {
                // Accept a connection.
                // The .accept() method BLOCKS (waits) until a client connects. When a client
                // connects, it returns a Socket object for communication.
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Handle the client's request. (IN a real-world server, you'd often start a new
                // thread here to handle the client, so the server can immediately go back to
                // the waiting for the next connection.)
                handleClientRequest(clientSocket);
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        } finally {
            try {
                if (serverSocket != null)
                    serverSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing server socket: " + e.getMessage());
            }
        }
    }

    private static void handleClientRequest(Socket clientSocket) {
        // try-with-resources for automatic closing of the streams and socket.
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true) // 'true' for auto-flush
        ) {

            // Read the command sent by the client (e.g., "GET test.tsx")
            String command = in.readLine();
            System.out.println("Received command from client: \"" + command + "\"");

            if (command != null && command.startsWith("GET ")) {
                // Extracting the filename from the command
                String filename = command.substring(4).trim(); // for GET request
                File file = new File("$06_networking_and_javafx/" + filename); // create a test.txt file in you the same
                                                                               // directory level of this file.

                if (file.exists() && !file.isDirectory()) {
                    out.println("OK");

                    // Send file contents
                    try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = fileReader.readLine()) != null) {
                            out.println(line);
                        }
                    }
                    System.out.println("Successfully sent file: " + filename);
                } else {
                    out.println("ERROR: File not found.");
                    System.err.println("Client requested a file that does not exist: " + filename);
                }
            } else {
                out.println("ERROR: Invalid command.");
                System.err.println("Received an invalid command from client.");
            }
        } catch (IOException e) {
            System.err.println("Error handling client request: " + e.getMessage());
        }
        // The client socket is automatically closed by the try-with-rescources block.
        System.out.println("Client disconnected.");
    }
}
