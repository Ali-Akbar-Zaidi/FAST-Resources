/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package echoserver;

import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author farooq
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket server;
        
        try {

            log("Starting server");
            server = new ServerSocket(4444);

            log("listening on port 4444");
            Socket client = null;
            do {
                client = server.accept();

                if (client != null){
                    EchoTask task = new EchoTask(client);
                    Thread thread = new Thread(task);
                    thread.start();
                }
                
            } while (client != null);
            
            log("shutting down");

        } catch (Exception ex) {
            log(ex.toString());
        }
    }

    public static void log(String message){
        System.out.println(message);
    }

}
