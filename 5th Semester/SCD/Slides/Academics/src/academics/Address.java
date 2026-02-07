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
public class Address {
    
    String stAdd;
    String city;
    String country;
    
    public Address(String s,String c,String co){
        stAdd = s;
        city = c;
        country = co;
    }
    
    public void print(){
        System.out.println("Address: " + stAdd + "," + city + "," + country );
    }
          
    
}
