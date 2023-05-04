/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package node;

/**
 *
 * @author manjot
 */
public class Node {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
            Creates a ConnectServer instance with the port number passed as an 
            argument and calls the connectSystem method to connect to the server.
        */
        ConnectServer connect = new ConnectServer(Integer.parseInt(args[0]));
        connect.connectSystem();
        
        /*
            Creates a ReceiveJobs instance with the port number passed as an 
            argument and calls the receiveLoad method to receive jobs from the 
            server.
        */
        ReceiveJobs receive = new ReceiveJobs(Integer.parseInt(args[0]));
        receive.receiveLoad();
    }

}
