/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loadbalancer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author manjot
 */
public class ServerSystem {

    private int serverPort = -1; // Private instance of port number on which server listens for incoming messages
    private DatagramSocket socket = null; // Private socket to receive and send datagrams

    // Constructor to set the server port
    public ServerSystem(int port) {
        serverPort = port;
    }
    
    // Method to run the server
    public void runSystem() {
        System.out.println("----------------------------------------");
        System.out.println("Server is running...");
        System.out.println("Listening on port number: " + serverPort);
        System.out.println("----------------------------------------");

        int totalJobsReceived = 0; // Counter for the total jobs received from client

        JobsManager jobManager = JobsManager.GETINSTANCE; // Singleton instance of JobsManager class
        NodesManager nodeManager = NodesManager.GETINSTANCE; // Singleton instance of NodesManager class

        try {
            socket = new DatagramSocket(serverPort); // Create a new socket to receive messages
            socket.setSoTimeout(0); // Set the timeout for receiving messages to infinite

            boolean repeat = true;

            while (repeat) {
                byte[] packetData = new byte[1024];
                DatagramPacket packet = new DatagramPacket(packetData, packetData.length);
                socket.receive(packet); // receive a packet
                String message = new String(packetData); // convert packet data into a string

                String[] elements = message.trim().split(","); // Split the message string into different elemetns
                switch (elements[0]) {
                    case "REG":
                        // this is a registration message from a node
                        System.out.println("---> got a REG instruction");

                        String ipAddress = elements[1];
                        String nodeCapacity = elements[2];
                        String portNumber = elements[3];
                        System.out.println("-----> Node IP = " + ipAddress + ", Job Capacity = " + nodeCapacity + ", Port = " + portNumber);

                        Nodes nodeClass = new Nodes(); // Create a new node object

                        nodeClass.setNodePort(portNumber); // Set node port
                        nodeClass.setNodeCapacity(nodeCapacity); // Set node capacity
                        nodeManager.addNode(nodeClass); // Add the node to the list of nodes
                        break;
                    case "JOB":
                        // this is a job message
                        System.out.println("---> got a JOB instruction");
                        String jobTime = elements[1];
                        String jobNumber = elements[2];
                        if (Integer.parseInt(jobNumber) >= 11) {
                            System.out.println("-----> Maximum job numbers reached");
                            break;
                        }
                        System.out.println("-----> Job Time = " + jobTime + ", Job Number = " + jobNumber);
                        totalJobsReceived ++; // Increment the total number of jobs by 1

                        Jobs jobStore = new Jobs(); // Create a new job object

                        jobStore.setJobNumber(jobNumber); // Set job number
                        jobStore.setJobTime(jobTime); // Set job time
                        jobManager.addJob(jobStore); // Add the job to the list of jobs
                        break;
                    case "STOP":
                        // this is a stop message
                        System.out.println("---> got a STOP instruction");
                        repeat = false; // Set the repeat value to false to stop receiving messages
                        break;
                    default:
                        System.out.println("---> Unknown istruction: " + elements[0]);
                }
            }
            System.out.println("-----> Blocking incoming traffic...");
        } catch (Exception error) {
            error.printStackTrace();
        } finally {
            // Start RoundRobin class in a new thread and pass server port number
            RoundRobin loadScheduling = new RoundRobin(serverPort); 
            loadScheduling.start();

            System.out.println("----------------------------------------");
            System.out.println("Processing jobs");
            System.out.println("----------------------------------------");

            try {
                boolean repeat = true;
                int totalJobsProcessed = 0;
                
                // Listen for incoming packets from the clients and process the jobs that were sent
                while (repeat) {
                    byte[] packetData = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(packetData, packetData.length);
                    socket.receive(packet);
                    String message = new String(packetData);
                    
                    // Extract the message elements
                    String[] elements = message.trim().split(",");
                    switch (elements[0]) {
                        // If the message is a "FINISHED" instruction, print a message indicating that a job has finished
                        case "FINISHED":
                            // this is a finish message
                            System.out.println("---> got a FINISHED instruction");
                            int finishedJobNumber = Integer.parseInt(elements[1]);
                            System.out.println("-----> the job no. " + finishedJobNumber + " has been finished");
                            totalJobsProcessed ++;
                            
                            // If all jobs have been processed exit the while loop
                            if (totalJobsProcessed == totalJobsReceived) {
                                repeat = false;
                            }
                            break;
                        // If the message is not recognised print a message
                        default:
                            System.out.println("---> Unknown instruction: " + elements[0]);
                    }
                }
                // Print messages indicating that all jobs have been processed and the system is now shutting down
                System.out.println("---> all JOBs have been processed");
                System.out.println("-----> Now shutting system down");
                System.out.println("-----> Closing socket");
                socket.close(); // Close socket
            } catch (Exception error) {
                error.printStackTrace(); // If there is an error print the stack trace
            } finally {
                // Run CloseNodes class to stop nodes
                CloseNodes closeNode = new CloseNodes(serverPort);
                closeNode.runSystem();
            }
        }
    }
}
