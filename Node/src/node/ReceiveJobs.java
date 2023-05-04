/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author manjot
 */
public class ReceiveJobs {

    private int nodePort = -1; // Private variable to store port number
    private DatagramSocket socket = null; // DatagramSocket object for sending and receiving datagram packets

    public ReceiveJobs(int port) {
        nodePort = port; // Initialise port number
    }

    public void receiveLoad() {
        System.out.println("--------------------------------------------------");
        System.out.println("Node is running...");
        System.out.println("Waiting for jobs on port number: " + nodePort);
        System.out.println("--------------------------------------------------");

        try {
            socket = new DatagramSocket(nodePort); // Create a DatagramSocket object for nodePort
            socket.setSoTimeout(0); // Set the socket timeout to zero so it waits indefinitely for packets

            boolean repeat = true;
            while (repeat) {
                byte[] packetData = new byte[1024]; // Create a byte array to store the received data 
                DatagramPacket packet = new DatagramPacket(packetData, packetData.length); // Create a new DatagramPacket object
                
                socket.receive(packet); // Receive a datagram packet

                String message = new String(packetData); // Convert the received data to a string
                String[] elements = message.trim().split(","); // Split the string into elements
                switch (elements[0]) {
                    case "JOB":
                        System.out.println("---> got a JOB");

                        JobProcessing processer = new JobProcessing(message, nodePort); // Create a new JobProcessing object to process the received job
                        processer.start(); // Start the job processing in a new thread

                        break;
                    case "STOP":
                        System.out.println("---> got a STOP instruction");
                        System.out.println("-----> shutting down system");
                        
                        repeat = false; // Stop receiving jobs
                        break;
                    default:
                        System.out.println("Unknown instruction..."); // Print an error message if the instruction is unknown
                }
            }
        } catch (Exception error) {
            error.printStackTrace(); // Print the stack trace if an exception occurs
        } finally {
            System.out.println("-----> Closing socket");
            socket.close(); // Close the socket after all jobs have been processed or an exception occurs
        }
    }
}
