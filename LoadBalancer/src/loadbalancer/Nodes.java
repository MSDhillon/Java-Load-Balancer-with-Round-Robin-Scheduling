/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loadbalancer;

/**
 *
 * @author manjot
 */
public class Nodes {
    
    // Private variables to store node capacity and node port
    private String nodeCapacity = "";
    private String nodePort = "";
    
    public Nodes() {
        
    }
    
    // Method to set node port
    public void setNodePort(String port) {
        nodePort = port;
    }
    
    // Method to get node port
    public String getNodePort() {
        return nodePort;
    }
    
    // Method to set node capacity
    public void setNodeCapacity(String capacity) {
        nodeCapacity = capacity;
    }
    
    // Method to get node capacity
    public String getNodeCapacity() {
        return nodeCapacity;
    }
}
