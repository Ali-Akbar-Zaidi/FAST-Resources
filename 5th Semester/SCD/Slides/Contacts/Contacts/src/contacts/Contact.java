/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacts;

/**
 *
 * @author farooq
 */
public class Contact {
    
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    
    public Contact(){
        
    }
    
    public Contact(String f, String l, String e, String c){
        firstName = f;
        lastName = l;
        email = e;
        contactNo = c;
    }
    
    public Object[] values(){
        Object[] val = new Object[4];
        val[0] = firstName;
        val[1] = lastName;
        val[2] = email;
        val[3] = contactNo;
        return val;
    }

    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getContactNo(){
        return contactNo;
    }
    
    public void setValues(String f, String l, String e, String c){
        firstName = f;
        lastName = l;
        email = e;
        contactNo = c;
    }
           
}
