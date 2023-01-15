package org.example.GroupCommunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class GroupClient2 {
    private static final String GROUP_ADDRESS = "230.0.0.1";
    private static final int PORT = 1234;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws IOException {
        // Create a new multicast socket
        try (MulticastSocket socket = new MulticastSocket(PORT)) {
            // Join the group
            InetAddress group = InetAddress.getByName(GROUP_ADDRESS);
            socket.joinGroup(group);

            while (true) {
                // Receive a message from the group
                byte[] buffer = new byte[BUFFER_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received message: " + message);
            }
        }
    }
}
