/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoPackage;

/**
 *
 * @author ALI
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Change according to your setup
    private static final String URL =
            "jdbc:sqlserver://localhost:1433;databaseName=dataBaseMid2;encrypt=true;trustServerCertificate=true";
    private static final String USER = "ALI";      // or your SQL Server username
    private static final String PASSWORD = "12344321";

    // Prevent object creation
    private DBConnection() {}

    // Static method
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (Exception e) {
            System.out.println("SQL Server JDBC Driver not found");
            e.printStackTrace();
        }
        return con;
    }
}
