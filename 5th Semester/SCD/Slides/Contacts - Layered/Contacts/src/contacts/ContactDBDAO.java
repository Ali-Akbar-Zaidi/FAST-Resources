/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author farooq
 */
public class ContactDBDAO implements IDAO {

    @Override
    public boolean save(Hashtable<String, String> data) {
        int count = 0;
        try{
            Connection conn = getConnection();            
            PreparedStatement stmt = updateStatement(conn,data);
            count = stmt.executeUpdate();
            if (count == 0){
                stmt = insertStatement(conn,data);
                count = stmt.executeUpdate();            
            }                        
        } catch(SQLException ex){
            return false;
        }
        return count > 0 ? true : false;
    }
    
    public Hashtable<String, String> load(String id){
        Hashtable<String,String> data = new Hashtable<String,String>();
        try{            
            Connection conn = getConnection();
            PreparedStatement stmt = selectSingleStatement(conn,id);
            ResultSet rs = stmt.executeQuery();
                    
            while (rs.next()){                
                data.put("id", rs.getString("id"));
                data.put("firstname", rs.getString("firstname"));
                data.put("lastname", rs.getString("lastname"));
                data.put("email", rs.getString("email"));
                data.put("contactno", rs.getString("contactno"));                
            }        
        } catch(SQLException ex){
            // log exception
        }
        return data;
    }
    
    public boolean delete(String id){        
        int count = 0;
        try{
            Connection conn = getConnection();
            PreparedStatement stmt = deleteStatement(conn,id);
            count = stmt.executeUpdate();            
        } catch(SQLException ex){
            return false;
        }
        return count > 0 ? true : false;
    }
    
    
    private PreparedStatement insertStatement(Connection conn,Hashtable<String,String> data) throws SQLException{
        String query = "insert into contact(id,firstname,lastname,email,contactno) values(?,?,?,?,?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        
        stmt.setString(1,data.get("id"));
        stmt.setString(2,data.get("firstname"));
        stmt.setString(3,data.get("lastname"));
        stmt.setString(4,data.get("email"));
        stmt.setString(5,data.get("contactno"));
        
        return stmt;
    }
    
    private PreparedStatement updateStatement(Connection conn,Hashtable<String,String> data) throws SQLException{
        String query = "update contact set firstname = ?, lastname = ?, email = ?, contactno = ? where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1,data.get("firstname"));
        stmt.setString(2,data.get("lastname"));
        stmt.setString(3,data.get("email"));
        stmt.setString(4,data.get("contactno"));
        stmt.setString(5,data.get("id"));
        return stmt;
    }
    
    private PreparedStatement selectSingleStatement(Connection conn,String id) throws SQLException{
        String query = "select id,firstname,lastname,email,contactno from contact where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1,id);
        return stmt;
    }
    
    private PreparedStatement deleteStatement(Connection conn,String id) throws SQLException{
        String query = "delete from contact where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1,id);
        return stmt;
    }    

    @Override
    public ArrayList<Hashtable<String, String>> load() {
        ArrayList<Hashtable<String,String>> data = new ArrayList<>();
        
        String query = "select id,firstname,lastname,email,contactno from contact";
        try {
          Connection conn = getConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          while (rs.next()) {
            Hashtable<String,String> o = new Hashtable<>();
            o.put("id",rs.getString("id"));
            o.put("firstname",rs.getString("firstname"));
            o.put("lastname",rs.getString("lastname"));
            o.put("email",rs.getString("email"));
            o.put("contactno",rs.getString("contactno"));
            
            data.add(o);
          }
        } catch (SQLException e) {
          
        }
        
        return data;
    }
    
    private static Connection getConnection()
            throws SQLException
    {           
        //return DriverManager.getConnection("jdbc:mysql://localhost/contacts","root","");       
        return DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=Contacts;user=dba;password=dba;encrypt=false;");  
    } 
    
}
