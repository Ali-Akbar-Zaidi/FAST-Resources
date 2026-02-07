/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package collections;

/**
 *
 * @author amyaw
 */
public class Main1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);
        list.print();
        
        System.out.println("---");
        LinkedList.Iterator iter = list.getIterator();
        while (iter.hasNext()){            
            System.out.println(iter.getData());
            iter.next();
        }
    }
    
}
