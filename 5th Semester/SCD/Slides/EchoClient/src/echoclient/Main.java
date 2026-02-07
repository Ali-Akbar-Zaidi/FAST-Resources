/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author farooq
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        	Socket socket;        

        try{

            socket = new Socket("localhost",4444);

            BufferedReader socketreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter socketwriter = new PrintWriter(socket.getOutputStream());

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a string or quit to exit the program");
            String input = consoleReader.readLine();
            
            while (!input.equals("quit")){
                socketwriter.println(input);
                socketwriter.flush();            
                System.out.println(socketreader.readLine()); 
                
                System.out.println("Enter a string or quit to exit the program");
                input = consoleReader.readLine();
            }

            socket.close();

        }
        catch(Exception ex){
		System.out.println("Exception:" + ex.getMessage());
        }

    }
    
}
