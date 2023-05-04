/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loadbalancer;

/**
 *
 * @author manjot
 */
public class LoadBalancer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
            The runSystem method is invoked to start the server and listen for 
            incoming requests after creating an instance of the ServerSystem 
            class with the port number passed as an argument.
        */
        ServerSystem server = new ServerSystem(Integer.parseInt(args[0]));
        server.runSystem();
        
    }

}
