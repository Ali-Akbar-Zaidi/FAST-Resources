package Controller.authentication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.Connect;
import Controller.schemas.Employee_Data;

public class Employee implements java.io.Serializable {
    public ArrayList<Employee_Data> employee_data = new ArrayList<Employee_Data>();

    public Employee() {
        load_data();
    }

    // read data from file and store in an array list of Employee schema
    public void load_data() {
        this.employee_data.clear();

        Connection conn = new Connect().getConnection();

        if (conn == null) {
            System.out.println("Error: Connection not established!");
            return;
        }

        try {
            String query = "SELECT * FROM Employee";
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Employee_Data data = new Employee_Data(rs.getString("username"), rs.getString("password"));
                employee_data.add(data);
            }
        } catch (SQLException ex) {
            System.out.println("Error: Error reading data from database");
            System.out.println(ex.getMessage());
        }

        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error: Unable to close connection");
            System.out.println(ex.getMessage());
        }
    };

    // write data to file after clearing it
    public void save_data() {
        Connection conn = new Connect().getConnection();

        if (conn == null) {
            System.out.println("Error: Connection not established!");
            return;
        }

        try {
            String query = "DELETE FROM Employee";
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

            for (int i = 0; i < employee_data.size(); i++) {
                Employee_Data data = employee_data.get(i);
                query = "INSERT INTO Employee (username, password) VALUES ('" + data.username + "', '" + data.password
                        + "')";
                stmt.executeUpdate(query);
            }
        } catch (SQLException ex) {
            System.out.println("Error: Error writing data to database");
            System.out.println(ex.getMessage());
        }

        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error: Unable to close connection");
            System.out.println(ex.getMessage());
        }
    }

    // check if employee already exists
    public boolean employee_exists(String username) {
        if (employee_data.size() == 0) {
            return false;
        }

        boolean flag = false;
        for (int i = 0; i < employee_data.size(); i++) {
            Employee_Data data = employee_data.get(i);
            if (data.username.equals(username)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    // signup an employee
    public boolean add_employee(String username, String password) {
        if (employee_exists(username) == true) {
            System.out.println("Error: Employee with Username already exists");
            return false;
        }
        Employee_Data new_data = new Employee_Data(username, password);
        employee_data.add(new_data);
        save_data();
        load_data();
        return true;
    }

    // change employee password
    public boolean change_password(String username, String password, String newPassword) {
        if (employee_data.size() == 0) {
            System.out.println("Error: Employee with this username doesn't exist");
            return false;
        }
        if (employee_exists(username) == false) {
            System.out.println("Error: Employee with this username doesn't exist");
            return false;
        }

        for (int i = 0; i < employee_data.size(); i++) {
            if (employee_data.get(i).username.equals(username) == true) {
                Employee_Data data = employee_data.get(i);
                if (data.password.equals(password) == false) {
                    System.out.println("Error: Invalid Username");
                    return false;
                } else if (data.password.equals(password) == true) {
                    String user = username;
                    String pass = newPassword;
                    employee_data.remove(i);
                    Employee_Data new_data = new Employee_Data(user, pass);
                    employee_data.add(new_data);
                }
                break;
            }
        }
        save_data();
        load_data();
        return true;
    }

    public boolean authenticate(String username, String password) {
        if (employee_data.size() == 0) {
            System.out.println("Error: Employee with this username doesn't exist");
            return false;
        }

        if (employee_exists(username) == false) {
            System.out.println("Error: Employee with this username doesn't exist");
            return false;
        }

        for (int i = 0; i < employee_data.size(); i++) {
            if (employee_data.get(i).username.equals(username) == true) {
                Employee_Data data = employee_data.get(i);
                if (data.password.equals(password) == false) {
                    System.out.println("Error: Invalid Password");
                    return false;
                } else if (data.password.equals(password) == true) {
                    return true;
                }
                break;
            }
        }
        return true;
    }
}