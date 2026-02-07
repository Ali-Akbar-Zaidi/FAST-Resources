/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package echoserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author farooq
 */
public class EchoTask implements Runnable {

    Socket client;

    public EchoTask(Socket client){
         this.client = client;
    }

    public void run() {
        
        try {
            System.out.println("client " + client.getPort() + " connected");
            PrintWriter writer = new PrintWriter(client.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            String message = "";
            while((message = reader.readLine()) != null){
                System.out.println(message);
                writer.println("Echo:" + message);
                writer.flush();
            }
            System.out.println("client " + client.getPort() + " disconnected");            
        }
        catch(Exception ex){

        }
    }

}
