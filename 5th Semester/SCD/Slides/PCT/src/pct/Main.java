/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pct;

/**
 *
 * @author amyaw
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(10);

        Producer p1 = new Producer(buffer);
        Producer p2 = new Producer(buffer);
        Consumer c1 = new Consumer(buffer);
        Consumer c2 = new Consumer(buffer);

        p1.start();
        c1.start();
        p2.start();
        c2.start();

    }

}
