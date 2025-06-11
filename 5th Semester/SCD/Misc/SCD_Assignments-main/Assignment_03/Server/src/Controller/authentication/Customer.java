package Controller.authentication;

import Controller.schemas.Customer_Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.Connect;

public class Customer implements java.io.Serializable {

    public ArrayList<Customer_Data> customer_data = new ArrayList<Customer_Data>();

    public Customer() {
        load_data();
    }

    public boolean match(String id, String cnic, String meter_type) {
        for (Customer_Data data : this.customer_data) {
            if (data.CNIC.equals(cnic) && data.Unique_ID.equals(id) && data.meter_type.equals(meter_type)) {
                return true;
            }
        }
        return false;
    }

    public boolean match(String id, String cnic) {
        for (Customer_Data data : this.customer_data) {
            if (data.CNIC.equals(cnic) && data.Unique_ID.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Customer_Data getCustomer(String cnic) {
        for (Customer_Data data : this.customer_data) {
            if (data.CNIC.equals(cnic)) {
                return data;
            }
        }
        return null;
    }

    public Customer_Data get_Cust_Index(int index) {
        return this.customer_data.get(index);
    }

    public void load_data() {
        this.customer_data.clear();

        Connection conn = new Connect().getConnection();

        if (conn == null) {
            System.out.println("Error: Connection not established!");
            return;
        }

        try {
            String query = "SELECT * FROM Customer";
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String unique_id = rs.getString("Unique_ID");
                String cnic = rs.getString("CNIC");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                String phone_no = rs.getString("Phone_No");
                String cust_type = rs.getString("cust_type");
                String meter_type = rs.getString("meter_type");
                String peak_units_consumed = rs.getString("peak_units_consumed");
                String reg_units_consumed = rs.getString("reg_units_consumed");

                Customer_Data data = new Customer_Data(unique_id, cnic, name, address, phone_no, cust_type, meter_type,
                        peak_units_consumed, reg_units_consumed);
                this.customer_data.add(data);
            }

        } catch (Exception ex) {
            System.out.println("Error: Error reading data from database");
            System.out.println(ex.getMessage());
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int count_meters(String cnic) { // only 3 allowed per cnic
        int count = 0;
        for (int i = 0; i < this.customer_data.size(); i++) {
            if (this.customer_data.get(i).CNIC.equals(cnic) == true) {
                count++;
            }
        }
        return count;
    }

    public boolean delete_from_unique_id(String id) {
        for (int i = 0; i < this.customer_data.size(); i++) {
            if (this.customer_data.get(i).Unique_ID.equals(id)) {
                this.customer_data.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean replace_customer_data_by_unique_id(Customer_Data customer_dt) {
        for (int i = 0; i < this.customer_data.size(); i++) {
            if (this.customer_data.get(i).Unique_ID.equals(customer_dt.Unique_ID)) {
                this.customer_data.set(i, customer_dt);
                return true;
            }
        }
        return false;
    }

    public String getMeterType(String cust_id) {
        for (Customer_Data data : this.customer_data) {
            if (data.Unique_ID.equals(cust_id)) {
                return data.meter_type;
            }
        }
        return null;
    }

    public void save_data() {
        Connection conn = new Connect().getConnection();

        // remove all rows from Customer table
        try {
            String query = "DELETE FROM Customer";
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println("Error: Error deleting data from database");
            System.out.println(ex.getMessage());
        }

        // insert all rows from customer_data
        for (Customer_Data data : this.customer_data) {
            try {
                String query = "INSERT INTO Customer (Unique_ID, CNIC, Name, Address, Phone_No, cust_type, meter_type, peak_units_consumed, reg_units_consumed) VALUES ('"
                        + data.Unique_ID + "', '" + data.CNIC + "', '" + data.Name + "', '" + data.Address + "', '"
                        + data.Phone_No + "', '" + data.cust_type + "', '" + data.meter_type + "', '"
                        + data.peak_units_comsumed + "', '" + data.reg_units_comsumed + "')";
                java.sql.Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);
            } catch (Exception ex) {
                System.out.println("Error: Error inserting data into database");
                System.out.println(ex.getMessage());
            }
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean add_meter(String unique_id, String cnic, String name, String address, String phone_no,
            String cust_type,
            String meter_type, String peak_units_consumed, String reg_units_consumed) {
        if (this.count_meters(cnic) >= 3) {
            System.out.println("Error: 3 Meters for this CNIC already exist!");
            return false;
        }
        Customer_Data data = new Customer_Data(unique_id, cnic, name, address, phone_no, cust_type, meter_type,
                peak_units_consumed,
                reg_units_consumed);
        this.customer_data.add(data);
        return true;
    }

    public boolean isUnique(String unique_id) {
        if (this.customer_data.size() == 0) {
            return true;
        }

        for (Customer_Data data : this.customer_data) {
            if (data.Unique_ID.equals(unique_id)) {
                return false;
            }
        }

        return true;
    }

    public int customer_size() {
        return this.customer_data.size();
    }

    public boolean add_to_units(String ID, String reg_units, String peak_units) {
        int i = 0;

        for (Customer_Data data : this.customer_data) {
            if (data.Unique_ID.equals(ID)) {
                if (peak_units != null && peak_units.equals("") == false && peak_units.equals("NULL") == false) {
                    int reg = Integer.parseInt(reg_units);
                    int peak = Integer.parseInt(peak_units);
                    int original_reg = Integer.parseInt(data.reg_units_comsumed);
                    int original_peak = Integer.parseInt(data.peak_units_comsumed);
                    data.reg_units_comsumed = String.valueOf(reg + original_reg);
                    data.peak_units_comsumed = String.valueOf(peak + original_peak);
                    this.customer_data.set(i, data);
                    return true;
                } else {
                    int reg = Integer.parseInt(reg_units);
                    int original_reg = Integer.parseInt(data.reg_units_comsumed);
                    data.reg_units_comsumed = String.valueOf(reg + original_reg);
                    this.customer_data.set(i, data);
                    return true;
                }
            }
            i++;
        }

        return false;
    }

    public boolean cust_id_exsts(String id) {
        if (this.customer_data.size() < 1) {
            return false;
        }

        for (Customer_Data data : this.customer_data) {
            if (data.Unique_ID.equals(id)) {
                return true;
            }
        }

        return false;
    }

}
