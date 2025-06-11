package Controller.authentication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.Connect;
import Controller.schemas.NADRA_Data;

public class NADRA implements java.io.Serializable {
    public ArrayList<NADRA_Data> nadra_data = new ArrayList<NADRA_Data>();

    public NADRA() {
        load_data();
    }

    public void load_data() {
        this.nadra_data.clear();

        Connection conn = new Connect().getConnection();

        if (conn == null) {
            System.out.println("Error: Connection not established!");
            return;
        }

        try {
            String query = "SELECT * FROM NADRA";
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                NADRA_Data data = new NADRA_Data(rs.getString("CNIC"), rs.getString("issue_date"),
                        rs.getString("expiry_date"));
                nadra_data.add(data);
            }
        } catch (Exception ex) {
            System.out.println("Error: Error reading data from database");
            System.out.println(ex.getMessage());
        }

        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error: Unable to close connection");
            System.out.println(ex.getMessage());
        }
    }

    // save data after clearing table
    public void save_data() {
        Connection conn = new Connect().getConnection();

        if (conn == null) {
            System.out.println("Error: Connection not established!");
            return;
        }

        try {
            String query = "DELETE FROM NADRA";
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

            for (int i = 0; i < this.nadra_data.size(); i++) {
                query = "INSERT INTO NADRA (CNIC, issue_date, expiry_date) VALUES ('" + this.nadra_data.get(i).CNIC
                        + "', '" + this.nadra_data.get(i).issue_date + "', '" + this.nadra_data.get(i).expiry_date
                        + "')";
                stmt.executeUpdate(query);
            }
        } catch (Exception ex) {
            System.out.println("Error: Error saving data to database");
            System.out.println(ex.getMessage());
        }

        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error: Unable to close connection");
            System.out.println(ex.getMessage());
        }
    }

    public boolean cnic_exits_except_index(String CNIC, int index) {
        for (int i = 0; i < this.nadra_data.size(); i++) {
            if (i == index) {
                continue;
            }
            if (this.nadra_data.get(i).CNIC.equals(CNIC)) {
                return true;
            }
        }
        return false;
    }

    public boolean exists(String CNIC) {
        if (this.nadra_data.size() == 0) {
            return false;
        }

        for (int i = 0; i < this.nadra_data.size(); i++) {
            if (this.nadra_data.get(i).CNIC.equals(CNIC)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(String CNIC, String issue_date, String expiry_date) {
        if (exists(CNIC) == true) {
            System.out.println("Error: CNIC already exists");
            return false;
        }

        NADRA_Data data = new NADRA_Data(CNIC, issue_date, expiry_date);
        this.nadra_data.add(data);
        this.save_data();
        this.load_data();
        return true;
    }

    public boolean update(String CNIC, String issue_date, String expiry_date) {
        if (exists(CNIC) == false) {
            System.out.println("Error: CNIC doesn't exist");
            return false;
        }

        for (int i = 0; i < this.nadra_data.size(); i++) {
            if (nadra_data.get(i).CNIC.equals(CNIC)) {
                NADRA_Data new_data = new NADRA_Data(CNIC, issue_date, expiry_date);
                this.nadra_data.set(i, new_data);
                this.save_data();
                this.load_data();
                return true;
            }
        }
        return false;
    }
}
