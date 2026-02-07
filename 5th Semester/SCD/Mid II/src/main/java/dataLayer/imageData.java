/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataLayer;
import java.util.*;
import daoPackage.*;
/**
 *
 * @author ALI
 */
public class imageData {
    private String path;
    private int id;
    private String Description;
    
    public imageData(int id, String path,  String Desc){
        this.path=path;
        this.id=id;
        this.Description=Desc;
    }
    public String getDesc(){
        return Description;
    }
    public String getPath(){
        return path;
    }
    public static List<imageData> loadImagesData(){
        idao db=new dbdao();
        List<Map<String,String>> myList=db.loadAll();
        List<imageData> result=new ArrayList<>();
        for(Map<String,String> mp:myList){
            int id=Integer.parseInt(mp.get("id"));
            String path=mp.get("imagePath");
            String desc=mp.get("description");
            imageData d=new imageData(id,path,desc);
            result.add(d);
           
        }
        return result;
    }
    
}
