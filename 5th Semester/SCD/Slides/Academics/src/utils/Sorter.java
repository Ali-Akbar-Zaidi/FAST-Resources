/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import academics.Comparable;
import academics.NotComparableException;

/**
 *
 * @author amyaw
 */
public class Sorter {
    public static <T extends Comparable> void sort(T[] data){
        try {
            for(int i=0; i < data.length-1; i++){
                for(int j=0; j < data.length-1; j++){
                    if (data[j].compare(data[j+1]) > 0){
                        T temp = data[j];
                        data[j] = data[j+1];
                        data[j+1] = temp;
                    }
                }
            }
        } catch(NotComparableException ex){
            
        }
    }
}
