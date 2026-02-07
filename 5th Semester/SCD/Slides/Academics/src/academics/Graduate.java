/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academics;

/**
 *
 * @author amyaw
 */
public class Graduate extends Student {
    
    public Graduate(String r, String n, Address a) {
        super(r, n, a);
    }
    
    public void print(){
        System.out.println("Graduate");
        super.print();
    }
    
}
