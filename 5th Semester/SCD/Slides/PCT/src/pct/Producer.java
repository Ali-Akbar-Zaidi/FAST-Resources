/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pct;


/**
 *
 * @author amyaw
 */
public class Producer extends Thread{
    BoundedBuffer buffer;
    
    public Producer(BoundedBuffer b){
        buffer = b;
    }
    
    public void run(){
        while(true){
            int v = (int) (Math.random()*10);
            
            buffer.insert(v);
            
            buffer.display();
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                
            }                    
        }
    }
    
}
