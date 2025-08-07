package $06_networking_and_javafx;

import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 8080; // Must match the server's port

        try (
                // Create a Socket to connect to the server
                Socket socket = new Socket(HOST, PORT);

                // Create a streams to communicate with the server.
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            System.out.println("Connected to file server at " + HOST + ":" + PORT);

            // Send a command to the server.
            String filenameToRequest = "test.txt";
            System.out.println("Requesting file: " + filenameToRequest);
            out.println("GET " + filenameToRequest);

            // Read the server's response.
            // The first line should be "OK" or an error message.
            String serverResponse = in.readLine();

            if (serverResponse != null && serverResponse.equals("OK")) {
                System.out.println("Server responded OK. File contents:");
                System.out.println("-----------------------------------");

                // Reading and printing every subsequent line sent by the server.
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println("-----------------------------------");
                System.out.println("End of file transmission.");
            } else {
                System.err.println("Server responded with an error: " + serverResponse);
            }
        } catch (UnknownHostException e) {
            System.err.println("Host not found: " + HOST);
        } catch (IOException e) {
            System.err.println("I/O error: Could not connect to the server. Is it running?");
        }
    }
}
