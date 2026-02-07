/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiclient;

import java.rmi.Naming;
import sorter.ISort;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

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
            ISort sorter = (ISort) Naming.lookup("sorter");
            
            List<Integer> list = new ArrayList<>();
            list.add(2);
            list.add(1);
            list.add(5);
            list.add(3);
            list.add(4);
            
            print(list);
            list = sorter.sort(list);
            print(list);
            
        } catch (Exception ex){
            System.out.println("Exception:" + ex.getMessage());
        }
            
    }
    
    private static void print(List<Integer> list){
        for (Integer i : list){
            System.out.print(i + " ");
        }
        System.out.println("");
    }
    
}
