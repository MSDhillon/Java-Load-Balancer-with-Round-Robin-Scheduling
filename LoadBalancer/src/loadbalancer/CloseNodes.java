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
 * 
 * The CloseNodes class provides a method to close all the nods connected 
 * to the system
 * 
 */
public class CloseNodes {

    int serverPort = -1;
    
    // Constructor that takes the server port as an argument from the ServerSystem
    public CloseNodes(int port) {
        serverPort = port;
    }
    
    // Method to run the CloseNodes system
    public void runSystem() {
        try {
            // Get the localhost address
            InetAddress address = InetAddress.getByName("localhost");
            // Get the NodesManager singleton and initialise variables
            NodesManager nodeManager = NodesManager.GETINSTANCE;
            int i = 0;
            int totalNodes = nodeManager.totNodes();
            
            // Iterate over each node and send a "STOP" message to its port
            while (i < totalNodes) {
                int port = nodeManager.getPort(i);

                String message = "STOP";
                DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address, port);
                DatagramSocket socket = new DatagramSocket(serverPort);
                
                socket.send(packet);
                socket.close();
                
                i++;

            }
        } catch (Exception error) {
            // Print stack trace in case of error
            error.printStackTrace();
        }
    }
}
