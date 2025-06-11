package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/A3";
        String username = "root"; // Default username in XAMPP MySQL
        String password = ""; // Default password is empty in XAMPP MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database successfully!");
            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Testing the connection
        Connection conn = getConnection();

        try {
            if (conn != null && conn.isValid(2)) {
                System.out.println("Connection is valid.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}