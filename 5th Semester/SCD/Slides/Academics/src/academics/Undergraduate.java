/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academics;

/**
 *
 * @author amyaw
 */
public class Undergraduate extends Student {
    
    public Undergraduate(String r, String n, Address a) {
        super(r, n, a);
    }
    
    public void print(){
        System.out.println("Undergraduate");
        super.print();
    }
    
}
