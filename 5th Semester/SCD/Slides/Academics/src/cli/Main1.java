/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cli;

import academics.Address;
import academics.Person;
import academics.Student;
import academics.Teacher;

/**
 *
 * @author amyaw
 */
public class Main1 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Address addr = new Address("FAST-NU, Block-B, Faisal Town","Lahore", "Pakistan");
        
        Person p1 = new Student("20-1234","Ahmed",addr);
        Person p2 = new Student("20-1235","Ali",addr);
        Person p3 = new Teacher("Assistant Professor","Farooq",addr);
        
        p1.print();
        p2.print();
        p3.print();
        
        try{
            System.out.println(p1.compare(p1));
            System.out.println(p1.compare(p2));
            System.out.println(p2.compare(p1));
            System.out.println(p2.compare(p3));
        }                
        catch (Exception ex){
            System.out.println(ex.getMessage());            
        }
        
    }
    
}
