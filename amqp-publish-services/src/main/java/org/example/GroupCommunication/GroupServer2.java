package org.example.GroupCommunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class GroupServer2 {
    private static final String GROUP_ADDRESS = "230.0.0.1";
    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        // Create a new multicast socket
        try (MulticastSocket socket = new MulticastSocket(PORT)) {
            // Join the group
            InetAddress group = InetAddress.getByName(GROUP_ADDRESS);
            socket.joinGroup(group);

            while (true) {
                // Send a message to the group
                String message = "Hello from the server!";
                byte[] messageBytes = message.getBytes();
                DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, group, PORT);
                socket.send(packet);
                System.out.println("Sent message: " + message);
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
