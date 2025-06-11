package Controller.billing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

import Connection.Connect;
import Controller.schemas.Tariff_Data;

public class TariffTax implements java.io.Serializable {

    public Tariff_Data domestic_1_phase = null;
    public Tariff_Data commercial_1_phase = null;
    public Tariff_Data domestic_3_phase = null;
    public Tariff_Data commercial_3_phase = null;

    public void update_tarrif(String reg, String peak, String percent, String fixed_charges, int option) {
        if (option == 1) {
            this.domestic_1_phase.reg_unit_price = reg;
            this.domestic_1_phase.peak_unit_price = peak;
            this.domestic_1_phase.percent_of_Tax = percent;
            this.domestic_1_phase.fixed_charges = fixed_charges;
        } else if (option == 2) {
            this.commercial_1_phase.reg_unit_price = reg;
            this.commercial_1_phase.peak_unit_price = peak;
            this.commercial_1_phase.percent_of_Tax = percent;
            this.commercial_1_phase.fixed_charges = fixed_charges;
        } else if (option == 3) {
            this.domestic_3_phase.reg_unit_price = reg;
            this.domestic_3_phase.peak_unit_price = peak;
            this.domestic_3_phase.percent_of_Tax = percent;
            this.domestic_3_phase.fixed_charges = fixed_charges;
        } else if (option == 4) {
            this.commercial_3_phase.reg_unit_price = reg;
            this.commercial_3_phase.peak_unit_price = peak;
            this.commercial_3_phase.percent_of_Tax = percent;
            this.commercial_3_phase.fixed_charges = fixed_charges;
        }

        save_data();
        load_data();
    }

    public TariffTax() {
        load_data();
    }

    public Tariff_Data getTax(String meter_type, String type) {
        if (meter_type.equals("1 Phase") && type.equals("Domestic")) {
            return this.domestic_1_phase;
        } else if (meter_type.equals("1 Phase") && type.equals("Commercial")) {

            return this.commercial_1_phase;
        } else if (meter_type.equals("3 Phase") && type.equals("Domestic")) {
            return this.domestic_3_phase;
        } else if (meter_type.equals("3 Phase") && type.equals("Commercial")) {

            return this.commercial_3_phase;
        }
        System.out.println("Error: Tax data not found");
        return null;
    }

    public void load_data() {
        // domestic_1_phase = new Tariff_Data();
        // commercial_1_phase = new Tariff_Data();
        // domestic_3_phase = new Tariff_Data();
        // commercial_3_phase = new Tariff_Data();
        Connection conn = new Connect().getConnection();
        try {

            String query = "SELECT * FROM TariffTax";
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(query);
            rs.next();
            domestic_1_phase = new Tariff_Data(rs.getString("Meter_Type"), rs.getString("reg_unit_price"),
                    rs.getString("peak_unit_price"), rs.getString("percent_of_Tax"), rs.getString("fixed_charges"));
            rs.next();
            commercial_1_phase = new Tariff_Data(rs.getString("Meter_Type"), rs.getString("reg_unit_price"),
                    rs.getString("peak_unit_price"), rs.getString("percent_of_Tax"), rs.getString("fixed_charges"));
            rs.next();
            domestic_3_phase = new Tariff_Data(rs.getString("Meter_Type"), rs.getString("reg_unit_price"),
                    rs.getString("peak_unit_price"), rs.getString("percent_of_Tax"), rs.getString("fixed_charges"));
            rs.next();
            commercial_3_phase = new Tariff_Data(rs.getString("Meter_Type"), rs.getString("reg_unit_price"),
                    rs.getString("peak_unit_price"), rs.getString("percent_of_Tax"), rs.getString("fixed_charges"));

        } catch (Exception e) {
            System.out.println("Error: Error loading data from database");
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error: Error closing connection");
                System.out.println(e.getMessage());
            }
        }
    }

    // save data after clearing database
    public void save_data() {
        Connection conn = new Connect().getConnection();
        try {
            String query = "DELETE FROM TariffTax";
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

            query = "INSERT INTO TariffTax (Meter_Type, reg_unit_price, peak_unit_price, percent_of_Tax, fixed_charges) VALUES ('"
                    + domestic_1_phase.Meter_Type + "','" + domestic_1_phase.reg_unit_price + "','"
                    + domestic_1_phase.peak_unit_price + "','" + domestic_1_phase.percent_of_Tax + "','"
                    + domestic_1_phase.fixed_charges + "')";
            stmt.executeUpdate(query);

            query = "INSERT INTO TariffTax (Meter_Type, reg_unit_price, peak_unit_price, percent_of_Tax, fixed_charges) VALUES ('"
                    + commercial_1_phase.Meter_Type + "','" + commercial_1_phase.reg_unit_price + "','"
                    + commercial_1_phase.peak_unit_price + "','" + commercial_1_phase.percent_of_Tax + "','"
                    + commercial_1_phase.fixed_charges + "')";
            stmt.executeUpdate(query);

            query = "INSERT INTO TariffTax (Meter_Type, reg_unit_price, peak_unit_price, percent_of_Tax, fixed_charges) VALUES ('"
                    + domestic_3_phase.Meter_Type + "','" + domestic_3_phase.reg_unit_price + "','"
                    + domestic_3_phase.peak_unit_price + "','" + domestic_3_phase.percent_of_Tax + "','"
                    + domestic_3_phase.fixed_charges + "')";
            stmt.executeUpdate(query);

            query = "INSERT INTO TariffTax (Meter_Type, reg_unit_price, peak_unit_price, percent_of_Tax, fixed_charges) VALUES ('"
                    + commercial_3_phase.Meter_Type + "','" + commercial_3_phase.reg_unit_price + "','"
                    + commercial_3_phase.peak_unit_price + "','" + commercial_3_phase.percent_of_Tax + "','"
                    + commercial_3_phase.fixed_charges + "')";
            stmt.executeUpdate(query);

        } catch (Exception e) {
            System.out.println("Error: Error saving data to database");
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error: Error closing connection");
                System.out.println(e.getMessage());
            }
        }
    }
}
