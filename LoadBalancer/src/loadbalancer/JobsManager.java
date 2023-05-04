/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loadbalancer;

import java.util.LinkedList;

/**
 *
 * @author manjot
 */
public enum JobsManager {
    
    GETINSTANCE; // Making the class into a Singleton

    private LinkedList<Jobs> jobsList = new LinkedList<Jobs>(); // LinkedList to store jobs
    
    // Method to add a job to the linked list
    public void addJob(Jobs job) {
        jobsList.add(job);
    }
    
    // Method to get total number of jobs in the linked list
    public int totJobs() {
        int counter = 0;
        for (Jobs j : jobsList) {
            counter += 1;
        }
        return counter;
    }
    
    // Method to get job stored at the given index
    public String getJob(int index) {
        int counter = 0;
        String job = "";
        for (Jobs j : jobsList) {
            if (counter == index) {
                String num = j.getJobNumber(); // Get job number
                String tim = j.getJobTime(); // Get processing time 
                job = num + "," + tim; // Combines job number and processing time as a string
                break;
            }
            counter += 1;
        }
        return job;
    }
}
