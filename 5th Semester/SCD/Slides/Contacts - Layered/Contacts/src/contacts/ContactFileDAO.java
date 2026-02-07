/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author farooq
 */
public class ContactFileDAO implements IDAO{

    File file;
    Hashtable<String,ArrayList<String>> contents;
    String delimiter = ";";
    
    public ContactFileDAO(String path){
        file = new File(path);        
        contents = new Hashtable<>();
        read();
    }
    
    @Override
    public boolean save(Hashtable<String, String> data) {
        
        ArrayList<String> row = new ArrayList<>();
        row.add(data.get("id"));
        row.add(data.get("firstname"));
        row.add(data.get("lastname"));
        row.add(data.get("email"));
        row.add(data.get("contactno"));
        
        if(contents.get(data.get("id")) != null){
            contents.replace(data.get("id"), row);
        }
        else{
            contents.put(data.get("id"), row);
        }
        
        write();
        
        return true;
    }

    @Override
    public boolean delete(String id) {
        contents.remove(id);
        write();
        return true;
    }

    @Override
    public Hashtable<String, String> load(String id) {
        Hashtable<String,String> data = new Hashtable<>();
        
        ArrayList<String> row = contents.get(id);
        data.put("id", row.get(0));
        data.put("firstname", row.get(1));
        data.put("lastname", row.get(2));
        data.put("email", row.get(3));
        data.put("contactno", row.get(4));        
        
        return data;               
    }

    @Override
    public ArrayList<Hashtable<String, String>> load() {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();
        read();
        for(ArrayList<String> row : contents.values()){
            String id = row.get(0);                        
            data.add(load(id));
        }
        
        return data;
    }
    
    private void read(){
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready()){
                String line = reader.readLine();
                String[] cols = line.split(delimiter);
                
                ArrayList<String> list = new ArrayList<>();
                for(String col: cols){
                    list.add(col);
                }
                
                contents.put(cols[0], list);                
            }
        }
        catch(IOException ex){
            
        }        
    }
    
    private void write(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(ArrayList<String> row : contents.values()){
                for(String col : row){
                    writer.append(col + delimiter);
                }
                writer.append("\n");
            }
            writer.flush();
            writer.close();
        }
        catch(IOException ex){
            
        }
    }
    
}
