/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academics;

/**
 *
 * @author farooq
 */
public class Teacher extends Person {
    private String designation;
    
    public Teacher(String desig,String n,Address a){
        super(n,a);
        designation = desig;
    }
    
    public void print(){
        System.out.println("Designation: " + designation);
        super.print();
    }
    
    public int compare(Comparable p) throws NotComparableException{
        if (p instanceof Teacher){
            Teacher t = (Teacher) p;
            
            return this.designation.compareToIgnoreCase(t.designation);
        }
        
        throw new NotComparableException();
    }
    
    
}
