/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package webclient;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author amyaw
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        WebServiceClient client = new WebServiceClient();
        String content = client.get("https://jsonplaceholder.typicode.com/users");        
        System.out.println(content);
        Collection<Map<String, Object>> collection = parse(content);
        for (Map<String,Object> map : collection){
            System.out.println(map.get("id") + "," + map.get("username") + "," + map.get("email"));             
        }
        
        /*
        String data = "{\"id\": 11,\"name\": \"Thomas\",    \"username\": \"thomas\",    \"email\": \"thomas@april.biz\" }";        
        WebServiceClient client = new WebServiceClient();
        String content = client.post("https://jsonplaceholder.typicode.com/users",data);        
        System.out.println(content);
        */
    }

    private static Collection<Map<String, Object>> parse(String content) throws JsonSyntaxException {
        Gson gson = new Gson();
        TypeToken<Collection<Map<String, Object>> > mapCollectionType = new TypeToken<Collection<Map<String, Object>>>(){};
        Collection<Map<String, Object>> mapCollection = gson.fromJson(content, mapCollectionType);
        return mapCollection;
    }
    
}
