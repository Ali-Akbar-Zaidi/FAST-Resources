/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacts;

import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author farooq
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Faizan","Ahmed","faizan@tech.edu","5674321"));
        contacts.add(new Contact("Ayesha","Anwar","ayesha@tech.edu","5674312"));
        contacts.add(new Contact("Hina","Hafeez","hina@tech.edu","5674132"));
        contacts.add(new Contact("Omer","Shafiq","omer@tech.edu","5674123"));
        
        ContactsUI ui = new ContactsUI(contacts);
        ui.setVisible(true);        
                
    }
    
}
