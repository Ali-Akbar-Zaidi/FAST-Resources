/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author amyaw
 */
public class Main2 {
    
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("Volvo");
        list.add("BMW");
        list.add("Ford");
        list.add("Mazda");
        
        Iterator<String> it = list.iterator();
        while (it.hasNext() ) {
            String s = it.next();
            System.out.println(s);
        }
        
        System.out.println("---");
        
        for(String s : list){
            System.out.println(s);
        }
    }
    
}
