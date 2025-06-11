package Controller.billing;

import Controller.schemas.Billing_Data;
import Controller.schemas.Customer_Data;
import Controller.schemas.Tariff_Data;
import java.util.ArrayList;

import Connection.Connect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import Controller.authentication.Customer;

public class Billing implements java.io.Serializable {
    public ArrayList<Billing_Data> billing_data = new ArrayList<Billing_Data>();

    public Billing() {
        load_data();
    }

    public int get_reports_paid() {
        if (this.billing_data.size() < 1) {
            return 0;
        }
        int paid = 0;
        for (Billing_Data data : this.billing_data) {
            if (data.bill_status.equals("Paid")) {
                paid++;
            }
        }
        return paid;
    }

    public int get_reports_unpaid() {
        if (this.billing_data.size() < 1) {
            return 0;
        }
        int unpaid = 0;
        for (Billing_Data data : this.billing_data) {
            if (data.bill_status.equals("Unpaid")) {
                unpaid++;
            }
        }
        return unpaid;
    }

    public String get_latest_date() {
        if (this.billing_data.size() < 1) {
            return null;
        }
        String latest_date = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate latest = LocalDate.MIN;
        for (Billing_Data data : this.billing_data) {
            LocalDate date = LocalDate.parse(data.reading_entry_date, formatter);
            if (date.isAfter(latest)) {
                latest = date;
                latest_date = data.reading_entry_date;
            }
        }
        return latest_date;
    }

    public boolean update_edit_billing_data(Billing_Data billinng_data) {
        for (int i = 0; i < this.billing_data.size(); i++) {
            Billing_Data dt = this.billing_data.get(i);
            if (dt.cust_id.equals(billinng_data.cust_id) && dt.billing_month.equals(billinng_data.billing_month)
                    && dt.billing_year.equals(billinng_data.billing_year)
                    && dt.reading_entry_date.equals(billinng_data.reading_entry_date)) {
                this.billing_data.set(i, billinng_data);
                return true;
            }
        }
        return false;
    }

    public boolean delete_bill(String id, String billing_month, String billing_year, String entry_date) {
        for (int i = 0; i < this.billing_data.size(); i++) {
            Billing_Data dt = this.billing_data.get(i);
            if (dt.cust_id.equals(id) && dt.billing_month.equals(billing_month)
                    && dt.billing_year.equals(billing_year)
                    && dt.reading_entry_date.equals(entry_date)) {
                this.billing_data.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Billing_Data> get_bill(String id) {
        ArrayList<Billing_Data> temp = new ArrayList<Billing_Data>();
        for (Billing_Data data : this.billing_data) {
            if (data.cust_id.equals(id)) {
                temp.add(data);
            }
        }
        return temp;
    }

    public void load_data() {
        this.billing_data.clear();

        Connection conn = new Connect().getConnection();
        if (conn == null) {
            System.out.println("Error: Unable to connect to database");
            return;
        }

        try {
            String query = "SELECT * FROM billing";
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Billing_Data data = new Billing_Data(rs.getString("cust_id"), rs.getString("billing_month"),
                        rs.getString("billing_year"), rs.getString("curr_meter_reading"),
                        rs.getString("curr_meter_reading_peak"),
                        rs.getString("reading_entry_date"), rs.getString("electricity_cost"), rs.getString("sales_tax"),
                        rs.getString("fixed_charges"), rs.getString("total_billing_amount"),
                        rs.getString("due_date"), rs.getString("bill_status"), rs.getString("bill_payment_date"));
                this.billing_data.add(data);
            }

        } catch (java.sql.SQLException e) {
            System.out.println("Error: Unable to fetch data from database");
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (java.sql.SQLException ex) {
                System.out.println("Error: Unable to close connection");
                System.out.println(ex.getMessage());
            }
        }
    }

    public String getMaxDate() // DD/MM/YYYY
    {
        if (this.billing_data.size() < 1) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate maxDate = null;

        for (Billing_Data data : this.billing_data) {
            try {
                LocalDate date = LocalDate.parse(data.reading_entry_date, formatter);
                if (maxDate == null || date.isAfter(maxDate)) {
                    maxDate = date;
                }
            } catch (DateTimeParseException e) {
                System.err.println("Error: Date format is incorrect: " + data.reading_entry_date);
            }
        }

        if (maxDate != null) {
            return maxDate.format(formatter);
        } else {
            return null;
        }
    }

    // save data after clearing all rows
    public void save_data() {
        Connection conn = new Connect().getConnection();
        if (conn == null) {
            System.out.println("Error: Unable to connect to database");
            return;
        }

        try {
            String query = "DELETE FROM billing";
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

            for (Billing_Data data : this.billing_data) {
                query = "INSERT INTO billing (cust_id, billing_month, billing_year, curr_meter_reading, curr_meter_reading_peak, reading_entry_date, electricity_cost, sales_tax, fixed_charges, total_billing_amount, due_date, bill_status, bill_payment_date) VALUES ('"
                        + data.cust_id + "', '" + data.billing_month + "', '" + data.billing_year + "', '"
                        + data.curr_meter_reading + "', '" + data.curr_meter_reading_peak + "', '"
                        + data.reading_entry_date
                        + "', '" + data.electricity_cost + "', '" + data.sales_tax + "', '" + data.fixed_charges
                        + "', '"
                        + data.total_billing_amount + "', '" + data.due_date + "', '" + data.bill_status + "', '"
                        + data.bill_payment_date + "')";
                stmt.executeUpdate(query);
            }

        } catch (java.sql.SQLException e) {
            System.out.println("Error: Unable to save data to database");
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (java.sql.SQLException ex) {
                System.out.println("Error: Unable to close connection");
                System.out.println(ex.getMessage());
            }
        }
    }

    public boolean add(Billing_Data data) {
        this.billing_data.add(data);
        return true;
    }
}
