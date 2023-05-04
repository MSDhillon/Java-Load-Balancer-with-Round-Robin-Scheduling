/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loadbalancer;

/**
 *
 * @author manjot
 */
public class Jobs {
    
    // Private variables to store job number and job time
    private String jobNumber = "";
    private String jobTime = "";

    public Jobs() {

    }
    
    // Method to set job number
    public void setJobNumber(String number) {
        jobNumber = number;
    }
    
    // Method to get job number
    public String getJobNumber() {
        return jobNumber;
    }
    
    // Method to set job time
    public void setJobTime(String time) {
        jobTime = time;
    }
    
    // Method to get job time
    public String getJobTime() {
        return jobTime;
    }
}
