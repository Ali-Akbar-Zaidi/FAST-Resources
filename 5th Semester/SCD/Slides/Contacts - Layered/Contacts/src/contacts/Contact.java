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
import java.util.UUID;

/**
 *
 * @author farooq
 */
public class Contact {
    
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    
    
    
    //private static IDAO dao = new ContactDBDAO();
    private static String path = "C:\\Users\\amyaw\\OneDrive\\Documents\\NetBeansProjects\\Contacts - Layered\\Contacts\\data.txt";
    private static IDAO dao = new ContactFileDAO(path);
    
    public Contact(){
        this.id = UUID.randomUUID().toString();
    }
    
    public Contact(String id){
        this.id = id;        
        load();
    }
    
    public Contact(String f, String l, String e, String c){
        this(UUID.randomUUID().toString(),f,l,e,c);        
    }
    
    public Contact(String i,String f, String l, String e, String c){
        id = i;
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
    
    //<editor-fold defaultstate="collapsed" desc="model interaction with database">
  
  /*  
    public boolean load(){
        
        try{
            Connection conn = getConnection();
            PreparedStatement stmt = selectSingleStatement(conn);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                id = rs.getString("id");
                firstName = rs.getString("firstname");
                lastName = rs.getString("lastname");
                email = rs.getString("email");
                contactNo = rs.getString("contactno");
            }        
        } catch(SQLException ex){
            return false;
        }
        return true;
        
    }
    
    public boolean save(){        
        int count = 0;
        try{
            Connection conn = getConnection();
            PreparedStatement stmt = updateStatement(conn);
            count = stmt.executeUpdate();
            if (count == 0){
                stmt = insertStatement(conn);
                count = stmt.executeUpdate();
            }
        } catch(SQLException ex){
            return false;
        } 
        return count > 0 ? true : false;
    }
    
    public boolean delete(){        
        int count = 0;
        try{
            Connection conn = getConnection();
            PreparedStatement stmt = deleteStatement(conn);
            count = stmt.executeUpdate();            
        } catch(SQLException ex){
            return false;
        } 
        return count > 0 ? true : false;        
    }
    
    
    private PreparedStatement insertStatement(Connection conn) throws SQLException{
        String query = "insert into contact(id,firstname,lastname,email,contactno) values(?,?,?,?,?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1,id);
        stmt.setString(2,firstName);
        stmt.setString(3,lastName);
        stmt.setString(4,email);
        stmt.setString(5,contactNo);
        return stmt;
    }
    
    private PreparedStatement updateStatement(Connection conn) throws SQLException{
        String query = "update contact set firstname = ?, lastname = ?, email = ?, contactno = ? where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1,firstName);
        stmt.setString(2,lastName);
        stmt.setString(3,email);
        stmt.setString(4,contactNo);
        stmt.setString(5,id);
        return stmt;
    }
    
    private PreparedStatement selectSingleStatement(Connection conn) throws SQLException{
        String query = "select id,firstname,lastname,email,contactno from contact where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1,id);
        return stmt;
    }
    
    private PreparedStatement deleteStatement(Connection conn) throws SQLException{
        String query = "delete from contact where id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);        
        stmt.setString(1,id);
        return stmt;
    }
    
    
    public static ArrayList<Contact> getContacts(){
        ArrayList<Contact> contacts = new ArrayList<>();        
        
        String query = "select id,firstname,lastname,email,contactno from contact";
        try {
          Connection conn = getConnection();
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          while (rs.next()) {
            String id = rs.getString("id");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String email = rs.getString("email");
            String contactno = rs.getString("contactno");
            
            contacts.add(new Contact(id,firstname,lastname,email,contactno));
          }
        } catch (SQLException e) {
          
        } 
        
        return contacts;
    }

    
    private static Connection getConnection()
            throws SQLException
    {           
        //return DriverManager.getConnection("jdbc:mysql://localhost/contacts","root","");
        return DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=Contacts;user=dba;password=dba;encrypt=false;");  
    }
    */
    // </editor-fold>   
    
    //<editor-fold defaultstate="collapsed" desc="layered architecture related code">
    
    public boolean load(){
        Hashtable<String,String> data = dao.load(id);
        if (data == null || data.isEmpty()){
            return false;
        }
        
        id = data.get("id");
        firstName = data.get("firstName");
        lastName = data.get("lastName");
        email = data.get("email");
        contactNo = data.get("contactNo");
        return true;
    }
    
    public boolean delete(){
        return dao.delete(id);
    }
    
    public boolean save(){        
        
        Hashtable<String,String> data = new Hashtable<>();
        data.put("id",id);
        data.put("firstname",firstName);
        data.put("lastname",lastName);
        data.put("email",email);
        data.put("contactno",contactNo);        
        return dao.save(data);
    }
    
    public static ArrayList<Contact> getContacts(){        
        ArrayList<Hashtable<String,String>> data = dao.load();
        ArrayList<Contact> contacts = new ArrayList<>();
        
        for(Hashtable<String,String> d : data){
            contacts.add(new Contact(d.get("id"),
                                    d.get("firstname"),
                                    d.get("lastname"),
                                    d.get("email"),
                                    d.get("contactno"))
            );
        }
        
        return contacts;
    }
    
    // </editor-fold>
    
}
