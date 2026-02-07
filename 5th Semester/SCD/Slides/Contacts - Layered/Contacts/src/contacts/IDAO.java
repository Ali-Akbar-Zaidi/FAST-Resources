/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacts;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author farooq
 */
public interface IDAO {
    
    public boolean save(Hashtable<String,String> data);    
    public boolean delete(String id);
    public Hashtable<String,String> load(String id);
    public ArrayList<Hashtable<String,String>> load();
    
}
