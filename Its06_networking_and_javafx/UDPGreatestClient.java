package $06_networking_and_javafx;

import java.net.*;

public class UDPGreatestClient {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 9999;

        try {
            // Create a DatagramSocket. We don't need to specify a port for the client.
            DatagramSocket socket = new DatagramSocket();

            // The data we want to send, as a string.
            String dataToSend = "15, 25";

            // Convert the string into a byte array;
            byte[] sendBuffer = dataToSend.getBytes();

            // Get the server's address.
            InetAddress serverAddress = InetAddress.getByName(HOST);

            // Create a DatagramPacket containing our data, aimed at the server.
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, PORT);

            // Send the packet.
            socket.send(sendPacket);
            System.out.println("Send numbers '" + dataToSend + "' to the server.");

            // --- Now, wait for the response ---
            byte[] recieveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(recieveBuffer, recieveBuffer.length);

            // Wait for a response packet to arrive
            socket.receive(receivePacket);

            // Convert the response bytes back into a string.
            String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server responded with the greatest number: " + result);

            socket.close();
        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
