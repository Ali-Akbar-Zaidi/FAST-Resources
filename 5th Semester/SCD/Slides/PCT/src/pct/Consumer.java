/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pct;

import static java.lang.Thread.sleep;

/**
 *
 * @author amyaw
 */
public class Consumer extends Thread{
    BoundedBuffer buffer;
    
    public Consumer(BoundedBuffer b){
        buffer = b;
    }
    
    public void run(){
        while(true){
            int v = buffer.remove();            
            
            buffer.display();
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                
            }                    
        }
    }
    
}
