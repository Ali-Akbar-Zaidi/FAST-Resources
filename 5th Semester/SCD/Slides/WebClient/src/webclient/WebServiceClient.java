/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author amyaw
 */
public class WebServiceClient {
    
    public String get(String url){
        StringBuilder content = new StringBuilder();
        try{
            URL service = new URL(url);
            URLConnection conn = service.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                                    conn.getInputStream()));
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                content.append(inputLine);
            }
            reader.close();
        } catch(Exception ex){
            
        }
        return content.toString();
    }
    
    public String post(String url,String data){
        StringBuilder response = new StringBuilder();
        try{
            URL service = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) service.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-type", "application/json");
            conn.setDoOutput(true);
            
            OutputStreamWriter request = new OutputStreamWriter(conn.getOutputStream());
            request.write(data);
                       
            
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(
                                    conn.getInputStream()));
            String inputLine;
            while ((inputLine = responseReader.readLine()) != null) {
                response.append(inputLine);
            }
            responseReader.close();
        } catch(Exception ex){
            
        }
        return response.toString();
    }    
    
}
