/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

import academics.*;

/**
 *
 * @author farooq
 */
public class Main2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Address addr = new Address("FAST-NU, Block-B, Faisal Town","Lahore", "Pakistan");
                
        Person[] persons = new Person[3];
        persons[0] = new Student("20-1234","Ahmed",addr);
        persons[1] = new Student("20-1235","Ali",addr);
        persons[2] = new Teacher("Assistant Professor","Farooq",addr);
        
        for(Person p : persons){
            p.print();
        }
        
    }
    
}
