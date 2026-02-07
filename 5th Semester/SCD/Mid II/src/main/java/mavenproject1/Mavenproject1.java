/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mavenproject1;
import java.sql.Connection;
import daoPackage.DBConnection;
import dataLayer.imageData;
import java.util.*;
import ViewLayer.myFrame;
import javax.swing.*;
/**
 *
 * @author ALI
 */
public class Mavenproject1 {
    
    public static void main(String[] args) {
       
      
        List<imageData> list = imageData.loadImagesData();
        SwingUtilities.invokeLater(()->{
            new myFrame(list);
        });
        
        
        
    }
}
