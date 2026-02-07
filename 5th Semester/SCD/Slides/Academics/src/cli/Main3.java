/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

import academics.*;
import utils.Sorter;

/**
 *
 * @author farooq
 */
public class Main3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Address addr = new Address("FAST-NU, Block-B, Faisal Town","Lahore", "Pakistan");
                
        Person[] persons = new Person[5];
        persons[0] = new Undergraduate("20-1234","Ahmed",addr);
        persons[1] = new Undergraduate("20-1235","Ali",addr);
        persons[2] = new Undergraduate("20-1232","Ayesha",addr);
        persons[3] = new Graduate("20-1236","Salman",addr);
        persons[4] = new Graduate("20-1230","Mariyam",addr);

        for(Person p : persons){
            p.print();
        }

        System.out.println( "---" );
        
        Sorter.sort(persons);
        
        for(Person p : persons){
            p.print();
        }
        
    }
    
}
