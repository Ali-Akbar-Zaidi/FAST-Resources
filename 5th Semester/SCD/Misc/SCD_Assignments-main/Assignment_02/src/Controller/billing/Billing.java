package Controller.billing;

import Controller.schemas.Billing_Data;
import Controller.schemas.Customer_Data;
import Controller.schemas.Tariff_Data;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import Controller.authentication.Customer;

public class Billing {
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

    public void view_bill(String cust_id, String CNIC) {
        int count = 0;
        int index = 1;
        Customer cust = new Customer();
        Customer_Data dt = cust.getCustomer(CNIC);
        if (dt == null) {
            System.out.println("Error: Invalid CNIC!");
            return;
        }

        for (Billing_Data data : this.billing_data) {
            if (data.cust_id.equals(cust_id) && dt.Unique_ID.equals(cust_id)) {
                if (cust.match(cust_id, CNIC) == true) {
                    Customer_Data cust_data = cust.getCustomer(CNIC);
                    String id = cust_data.Unique_ID;
                    String name = cust_data.Name;
                    String address = cust_data.Address;
                    String phone_no = cust_data.Phone_No;
                    TariffTax tax = new TariffTax();
                    String temp_mt = "";
                    if (dt.meter_type.equals("Single Phase")) {
                        temp_mt = "1 Phase";
                    } else {
                        temp_mt = "3 Phase";
                    }
                    Tariff_Data tax_data = tax.getTax(temp_mt, cust_data.cust_type);
                    String meter_t = tax_data.Meter_Type;
                    String reg_price = tax_data.reg_unit_price;
                    String peak_price = tax_data.peak_unit_price;
                    String tax_percent = tax_data.percent_of_Tax;
                    System.out.println("Index: " + index);
                    System.out.println("Due Date: " + data.due_date);
                    System.out.println("Payment Status: " + data.bill_status);
                    System.out.println("Customer ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Address: " + address);
                    System.out.println("Phone No: " + phone_no);
                    System.out.println("Fixed Charges: " + data.fixed_charges);
                    System.out.println("Metertype: " + meter_t);
                    System.out.println("Regular Unit Price: " + reg_price);
                    if (peak_price.equals("") == true) {
                        peak_price = "NULL";
                    }
                    System.out.println("Peak Unit Price: " + peak_price);
                    System.out.println("Tax Percentage: " + tax_percent);
                    System.out.println("Electricity Cost: " + data.electricity_cost);
                    System.out.println("Total Billing Amount: " + data.total_billing_amount);

                    System.out.println("");
                    System.out.println("");
                    count++;
                    index++;
                }
            }
        }
        if (count == 0) {
            System.out.println("Error: No data found against the given info!");
            System.out.println("");
        }

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

    public void view_bill(String cust_id, String CNIC, String meter_type, String reg_hours, String peak_hours) {
        int count = 0;
        int index = 1;
        Customer cust = new Customer();
        for (Billing_Data data : this.billing_data) {
            if (data.cust_id.equals(cust_id) && data.curr_meter_reading.equals(reg_hours)
                    && data.curr_meter_reading_peak.equals(peak_hours)) {
                if (cust.match(cust_id, CNIC, meter_type) == true) {
                    Customer_Data cust_data = cust.getCustomer(CNIC);
                    String id = cust_data.Unique_ID;
                    String name = cust_data.Name;
                    String address = cust_data.Address;
                    String phone_no = cust_data.Phone_No;
                    TariffTax tax = new TariffTax();
                    String temp_mt = "";
                    if (meter_type.equals("Single Phase")) {
                        temp_mt = "1 Phase";
                    } else {
                        temp_mt = "3 Phase";
                    }
                    Tariff_Data tax_data = tax.getTax(temp_mt, cust_data.cust_type);
                    String meter_t = tax_data.Meter_Type;
                    String reg_price = tax_data.reg_unit_price;
                    String peak_price = tax_data.peak_unit_price;
                    String tax_percent = tax_data.percent_of_Tax;
                    System.out.println("Index: " + index);
                    System.out.println("Due Date: " + data.due_date);
                    System.out.println("Payment Status: " + data.bill_status);
                    System.out.println("Customer ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Address: " + address);
                    System.out.println("Phone No: " + phone_no);
                    System.out.println("Fixed Charges: " + data.fixed_charges);
                    System.out.println("Metertype: " + meter_t);
                    System.out.println("Regular Unit Price: " + reg_price);
                    if (peak_price.equals("") == true) {
                        peak_price = "NULL";
                    }
                    System.out.println("Peak Unit Price: " + peak_price);
                    System.out.println("Tax Percentage: " + tax_percent);
                    System.out.println("Electricity Cost: " + data.electricity_cost);
                    System.out.println("Total Billing Amount: " + data.total_billing_amount);

                    System.out.println("");
                    System.out.println("");
                    count++;
                    index++;
                }
            }
        }
        if (count == 0) {
            System.out.println("Error: No data found against the given info!");
            System.out.println("");
        }
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

        BufferedReader reader = null;
        FileReader f_reader = null;

        try {
            f_reader = new FileReader("storage/BillingInfo.csv");
            reader = new BufferedReader(f_reader);

            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String parts[] = line.split(",");

                Billing_Data data = new Billing_Data(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5],
                        parts[6], parts[7], parts[8], parts[9], parts[10], parts[12], parts[11]);
                this.billing_data.add(data);
            }

        } catch (IOException ex) {
            System.out.println("Error: File not opened!");
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (f_reader != null) {
                    f_reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error: File not closed");
                System.out.println(e.getMessage());
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

    public void save_data() {
        FileWriter writer = null;

        try {
            writer = new FileWriter("storage/BillingInfo.csv", false);
            for (int i = 0; i < billing_data.size(); i++) {
                writer.write(billing_data.get(i).getString() + "\n");
            }

        } catch (IOException ex) {
            System.out.println("Error: Error writing data to file");
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Error: Unable to close Writer");
                System.out.println(e.getMessage());
            }
        }
    }

    public void print() {
        int index = 1;
        for (Billing_Data data : this.billing_data) {
            String peak;
            if (data.curr_meter_reading_peak.equals("")) {
                peak = "NULL";
            } else {
                peak = data.curr_meter_reading_peak;
            }
            String date;
            if (data.bill_payment_date.equals("")) {
                date = "NULL";
            } else {
                date = data.bill_payment_date;
            }
            String line;
            line = String.join(", ", data.cust_id, data.billing_month, data.billing_year, data.curr_meter_reading,
                    peak, data.reading_entry_date, data.electricity_cost, data.sales_tax, data.fixed_charges,
                    data.total_billing_amount, data.due_date, data.bill_status, date);

            System.out.println(String.valueOf(index) + ". " + line);
            index++;
        }
    }

    public boolean add(Billing_Data data) {
        this.billing_data.add(data);
        return true;
    }
}
