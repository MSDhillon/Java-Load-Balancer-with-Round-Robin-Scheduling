/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

/**
 *
 * @author manjot
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Calling the class ClientSystem and running the runSystem function
        ClientSystem client = new ClientSystem();
        client.runSystem(Integer.parseInt(args[0]));
        /* The argument has been passed as the port number, which the system is
        going to interpret as an integer */
    }
}
