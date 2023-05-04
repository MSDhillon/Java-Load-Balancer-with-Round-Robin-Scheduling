/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author manjot
 */
public class ProcessFinished {

    public void sendFinish(String jobNumber, int port) {
        
        try {
            
            InetAddress address = InetAddress.getByName("localhost");

            String message = "FINISHED," + jobNumber; // Create a message to send to the load balancer
            port += 1;
            
            // Create DatagramPacket with message, destination address and port number
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address, 7000);
            DatagramSocket socket = new DatagramSocket(port); // Create DatagramSocket using the node port

            socket.send(packet); // Send packet through socket
            socket.close(); // Close socket

        } catch (Exception error) {
            // Print stack trace if error occurs
            error.printStackTrace();

        }

    }

}
