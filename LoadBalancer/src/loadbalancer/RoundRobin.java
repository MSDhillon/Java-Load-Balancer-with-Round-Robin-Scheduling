/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loadbalancer;

/**
 *
 * @author manjot
 */
public class RoundRobin extends Thread {
    // Private variables to store current node number and the local server port number
    private int currentNode = 0;
    private int serverPort = -1;
    
    // Contructor to set the server port and increment it by 1
    public RoundRobin(int port) {
        serverPort = port;
        serverPort += 1;
    }

    @Override
    public void run() {

        try {

            NodesManager nManager = NodesManager.GETINSTANCE; // Signleton instance of NodesManager class
            JobsManager jManager = JobsManager.GETINSTANCE; // Singleton instance of JobsManager class
            SendJob sendJob = new SendJob(serverPort); // SendJob instance to send jobs to nodes

            String message = ""; // Message to be sent to the nodes

            int totalNodes = nManager.totNodes(); // Total number of nodes stored
            int totalJobs = jManager.totJobs(); // Total number of jobs stored
            int counter = 0; // Counter keeping track of the current node index

            for (int i = 0; i < totalJobs; i++) {

                try {

                    String jobData = jManager.getJob(i); // Get job data from the JobsManager
                    
                    // If all nodes have been used, reset the counter and currentNode
                    if (counter >= totalNodes) {
                        counter = 0;
                        currentNode = 0;
                    }

                    String nodeData = nManager.getNode(currentNode); // Get node data from NodesManager
                    String[] nodeElements = nodeData.trim().split(","); // Split node data into elements
                    int nodePort = Integer.parseInt(nodeElements[0]); // Extract the node port from node data

                    message = "JOB," + jobData; // Construct message to be sent to the node

                    sendJob.run(message, nodePort);
                    Thread.sleep(1000); // Sleep the program for 1 second
                    
                    // Increment currentNode and counter by 1
                    currentNode ++;
                    counter ++;

                } catch (NumberFormatException nfe) {
                    System.err.println("NumberFormatException occurred: " + nfe.getMessage());
                }

            }

        } catch (Exception error) {

            error.printStackTrace();

        }

    }

}
