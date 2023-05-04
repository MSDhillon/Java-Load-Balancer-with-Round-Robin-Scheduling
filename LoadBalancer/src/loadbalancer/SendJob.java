/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loadbalancer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author manjot
 */
public class SendJob {
    
    // Private variable to store port number for the local server
    private int serverPort = -1;
    
    // Constructor to set the port number
    public SendJob(int port) {
        serverPort = port;
    }
    
    // Method to send a message to a node with a specified port number
    public void run(String message, int nPort) {
        // Store message and node port number in local variables
        String packetMessage = message;
        int nodePort = nPort;
        
        try {
            // Get IP address of the local host
            InetAddress address = InetAddress.getByName("localhost");
            // Create a datagram packet containing the message and destination address and port
            DatagramPacket packet = new DatagramPacket(packetMessage.getBytes(), packetMessage.getBytes().length, address, nodePort);
            // Create a datagram socket bound to the local server port
            DatagramSocket socket = new DatagramSocket(serverPort);

            socket.send(packet); // Send packet using the created socket
            socket.close(); // Close socket

        } catch (Exception error) {
            // Print stack trace if error occurs
            error.printStackTrace();

        }

    }

}
