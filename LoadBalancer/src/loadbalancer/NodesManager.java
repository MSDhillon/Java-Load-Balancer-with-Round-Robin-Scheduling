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
public enum NodesManager {

    GETINSTANCE; // Making the class into a Singleton

    private LinkedList<Nodes> nodesList = new LinkedList<>(); // LinkedList to store nodes
    
    // Method to add a node to the linked list
    public void addNode(Nodes node) {
        nodesList.add(node);
    }

    // Method to get total number of nodes in the linked list
    public int totNodes() {
        int counter = 0;
        for (Nodes n : nodesList) {
            counter += 1;
        }
        return counter;
    }
    
    // Method to get node stored at the given index
    public String getNode(int num) {
        int counter = 0;
        String nod = "";
        for (Nodes n : nodesList) {
            if (counter == num) {
                String cap = n.getNodeCapacity(); // Get node capacity
                String por = n.getNodePort(); // Get node port number
                nod = por + "," + cap; // Combines port number and node capacity as a string
                break;
            }
            counter += 1;
        }
        return nod;
    }

    // Method to get node port stored at the given index
    public int getPort(int index) {
        String port = "";
        int counter = 0;
        for (Nodes n : nodesList) {
            if (counter == index) {
                port = n.getNodePort(); // Get node port number
                break;
            }
            counter += 1;
        }
        int node = Integer.parseInt(port);
        return node;
    }

}
