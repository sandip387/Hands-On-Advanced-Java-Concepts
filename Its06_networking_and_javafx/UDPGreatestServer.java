package $06_networking_and_javafx;

import java.net.*;

public class UDPGreatestServer {
    public static void main(String[] args) {
        final int PORT = 9999;
        try {
            // Create a DatagramSocket to listen on a port.
            // Unlike a ServerSocket, this doesn't "accept" connections. It just listens for
            // packets.
            DatagramSocket socket = new DatagramSocket(PORT);
            System.out.println("UDP Server is running on port " + PORT + "...");

            // Create a buffer to hold incoming data.
            byte[] receivedBuffer = new byte[1024];

            while (true) {
                // Create a DatagramPacket to recieve data into our buffer.
                DatagramPacket receivePacket = new DatagramPacket(receivedBuffer, receivedBuffer.length);
                
                // Wait for a packet to arrive. This is a blocking call.
                socket.receive(receivePacket);
                System.out.println("Received a packet from client.");

                // Convert the recieved bytes into a String.
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Client data: \"" + receivedData + "\"");

                // --- Process the data ---
                String[] numbers = receivedData.split(",");
                int num1 = Integer.parseInt(numbers[0].trim());
                int num2 = Integer.parseInt(numbers[1].trim());
                int greatest = Math.max(num1, num2);
                String result = String.valueOf(greatest);

                // --- Send the response back ---
                // Get the client's address and port from the packet they sent us.
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                
                // Convert our string response into bytes.
                byte[] sendBuffer = result.getBytes();
                
                // Create a new packet with the response data, aimed at the client's address.
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress,
                clientPort);
                
                // Send the response packet.
                socket.send(sendPacket);
                System.out.println("Sent response '" + result + "' back to the client.");
            }
        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
