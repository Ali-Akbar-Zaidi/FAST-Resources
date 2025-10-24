/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package collections;

import java.util.Hashtable;
import java.util.Iterator;


/**
 *
 * @author amyaw
 */
public class Main5 {
    public static void main(String[] args) {
        // Create a HashMap object called capitalCities
        Hashtable<String, String> capitalCities = new Hashtable<>();

        // Add keys and values (Country, City)
        capitalCities.put("England", "London");
        capitalCities.put("India", "New Dehli");
        capitalCities.put("Austria", "Wien");
        capitalCities.put("Norway", "Oslo");
        capitalCities.put("Norway", "Oslo"); // Duplicate
        capitalCities.put("USA", "Washington DC");

        System.out.println(capitalCities);
        System.out.println("---");
        
        for (String s : capitalCities.keySet()) {
            System.out.println("key: " + s + "; value: " + capitalCities.get(s));
        }
        
        System.out.println("---");
        Iterator<String> it = capitalCities.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            System.out.println("key: " + key + "; value: " + capitalCities.get(key));
        }
    }    
}
