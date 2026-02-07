/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author farooq
 */
public class Utils {
    
    public static String LOG_FILE = "C:\\Users\\amyaw\\Log.txt";
    public static Logger logger = Logger.getLogger("Academics");
    
    static {        
        try {
            logger.addHandler(new FileHandler(LOG_FILE));
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
    
    public static void log(String message){
    /*    File file = new File(LOG_FILE);
        //FileWriter writer = null;
        
        try {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());            
            
            writer.append("[" + timeStamp + "] " +  message + "\n");
            return ;
        }
        catch(IOException ex){
            ex.printStackTrace();
            return ;
        }
        finally{
            if(writer != null){
                try{
                    writer.close();
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            System.out.println("Exiting from log function");
        }*/
        
        
        
        logger.log(Level.SEVERE,message);
    }
}
