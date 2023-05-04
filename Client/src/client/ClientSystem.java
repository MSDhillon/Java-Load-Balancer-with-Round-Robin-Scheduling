/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author manjot
 */
public class ClientSystem {

    public void runSystem(int port) {
        try {
            InetAddress address = InetAddress.getByName("localhost"); // Getting host on localhost
            
            boolean loop = true;
            int counter = 0;

            Scanner input = new Scanner(System.in); // Scanner for scanning user input
            while (loop) {
                System.out.println("---> Do you wish to send a job to the load balancer? (y/n)");
                String i = input.nextLine();
                if (i.equals("y")) {
                    System.out.println("-----> Input time of job: ");
                    int time = input.nextInt();
                    input.nextLine();

                    counter += 1;

                    String message = "JOB," + time + "," + counter;

                    DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address, 7000); // Making a new datagram packet for transmission to the load balancer
                    DatagramSocket socket = new DatagramSocket(port); // Creating a new datagram socket to send the datagram packet

                    socket.send(packet); // Sending the datagram packet to the load balancer
                    socket.close(); // Closing the datagram socket after sending the packet
                } else if (i.equals("n")) {
                    System.out.println("Jobs sent to the load balancer: " + counter);
                    
                    String message = "STOP";
                    
                    DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address, 7000); // Creating a new datagram packet to send to the load balancer
                    DatagramSocket socket = new DatagramSocket(port); // Creating a new datagram socket to send the datagram packet
                    
                    socket.send(packet); // Sending the datagram packet to the load balancer
                    socket.close(); // Closing the datagram socket after sending the packet
                    
                    System.out.println("System shutting down...");
                    loop = false; // Exiting the loop as the user does not wish to send any more jobs
                    
                } else {
                    
                    System.out.println("Wrong input...");
                    
                }
            }

        } catch (Exception error) {
            
        }
    }
}
