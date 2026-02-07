/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package collections;

/**
 *
 * @author amyaw
 */
public class LinkedList<T>{
    
    private class Node {
        public T data;
        Node next = null;
    }
    
    private Node head,tail;
    private int size;
    
    public LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }
    
    public void add(T data){
        Node node = new Node();
        node.data = data;
        
        if (head == null){
            head = node;
            tail = node;
        }
        else if (head.next == null){
            tail = node;
            head.next = tail;
        } else{
            tail.next = node;
            tail = node;
        }
        
        size++;
    }
    
    public void print(){
        Node temp = head;
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
    
    public Iterator getIterator(){
        return new Iterator();
    }
    
    public class Iterator {
        private Node current;
        
        public Iterator(){
            current = head;
        }
        
        T getData(){
            return current.data;
        }
        
        boolean hasNext(){
            if(current != null){
                return true;
            }
            return false;
        }
        
        void next(){
            if (current != null){
                current = current.next;
            }
        }
    }
    
}
