/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academics;

/**
 * @author farooq
 */
public abstract class Person implements Comparable, Printable {
    
    String name;
    Address address;
    
    public Person(String n, Address a){
        name = n;
        address = a;
    }
    
    public void print(){
        System.out.println("Name:" + name);
        address.print();
        System.out.println("---");
    }
    
    public abstract int compare(Comparable p) throws NotComparableException;
}
