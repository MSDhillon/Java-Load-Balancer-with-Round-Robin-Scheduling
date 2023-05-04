/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

/**
 *
 * @author manjot
 */
public class ConnectServer {
    // Created a private variable to store port number
    private int port = -1;
    
    // Construct to set the port number
    public ConnectServer(int p) {
        port = p;
    }

    public void connectSystem() {

        try {
            InetAddress address = InetAddress.getByName("localhost"); // Get IP address of local address

            // Generate IP Address and load capacity for the node
            Random random = new Random();
            int ip = random.nextInt(255);
            int loadCapacity = random.nextInt(1, 5);
            String ipAddress = "192.168.1." + ip;
            
            // Create registration message to be sent to the server
            String message = "REG," + ipAddress + "," + loadCapacity + "," + port;
            
            // Create a DatagramPacket to send message
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address, 7000);
            DatagramSocket socket = new DatagramSocket(port); // Create socket to send packet

            socket.send(packet); // Send packet
            socket.close(); // Close socket
        } catch (Exception error) {
            error.printStackTrace(); // Print stack trace if an error occurs
        }
    }
}
