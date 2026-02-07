/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author farooq
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        try{
            Registry registry = LocateRegistry.createRegistry(1099);            
        } catch(Exception ex){
            System.out.println("Registry already exists");
        }
        
        try{         
            Naming.rebind("sorter", new SortServer());
            System.out.println("SortServer bound to rmi registry");
        } catch(Exception ex){
            System.out.println("Exception:" + ex.getMessage());
        }
    }
    
}
