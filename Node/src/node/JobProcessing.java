/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

/**
 *
 * @author manjot
 */
public class JobProcessing extends Thread {

    private String load = ""; // Initialise private empty job string
    private int nodePort = -1; // Initialise private node port

    public JobProcessing(String job, int port) {
        load = job; // Assign job string to load variable
        nodePort = port; // Assign port number to nodePort variable
    }

    @Override
    public void run() {

        ProcessFinished sendMessage = new ProcessFinished(); // Create instance of ProcessFinished class

        try {
            System.out.println("Starting to process job");

            String[] element = load.trim().split(","); // Split job string by commas
            // Check if the job string has 3 elements 
            if (element.length >= 3) {
                String jobNumber = element[1]; // Assign second element to jobNumber variable
                int burstTime = Integer.parseInt(element[2]); // Assign third element to burstTime variable

                burstTime *= 1000; // Convert burstTime from seconds to milliseconds

                Thread.sleep(burstTime); // Pause thread for the amount of time stored by burstTime

                sendMessage.sendFinish(jobNumber, nodePort); // Send message to indicate job has finished
            } else {
                System.err.println("Invalid input format: " + load); // Print error message if job string doesn't have enough elements
            }

        } catch (Exception error) {
            error.printStackTrace(); // Print stack trace if any exception occurs
        }
    }
}
