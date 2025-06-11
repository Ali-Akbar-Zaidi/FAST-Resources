package authentication;

import schemas.Customer_Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class Customer {

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

    public void print_data() {
        int index = 1;
        for (Customer_Data data : customer_data) {
            String line = data.Unique_ID + ", " + data.CNIC + ", " + data.Name + ", " + data.Address + ", "
                    + data.Phone_No + ", " + data.cust_type + ", " + data.meter_type + ", " + data.reg_units_comsumed;
            String peak_units = "";
            if (data.meter_type.equals("Single Phase")) {
                peak_units = "NULL";
            } else {
                {
                    peak_units = data.peak_units_comsumed;
                }
            }
            line = line + ", " + peak_units;
            line = Integer.toString(index) + ". " + line;
            System.out.println(line);
            index++;
        }
    }

    public Customer_Data get_Cust_Index(int index) {
        return this.customer_data.get(index);
    }

    public void load_data() {
        this.customer_data.clear();

        BufferedReader reader = null;
        FileReader f_reader = null;

        try {
            f_reader = new FileReader("storage/CustomersInfo.csv");
            reader = new BufferedReader(f_reader);

            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String parts[] = line.split(",");

                Customer_Data data = new Customer_Data(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5],
                        parts[6], parts[7], parts[8]);
                this.customer_data.add(data);
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

    public int count_meters(String cnic) { // only 3 allowed per cnic
        int count = 0;
        for (int i = 0; i < this.customer_data.size(); i++) {
            if (this.customer_data.get(i).CNIC.equals(cnic) == true) {
                count++;
            }
        }
        return count;
    }

    public void save_data() {
        FileWriter writer = null;

        try {
            writer = new FileWriter("storage/CustomersInfo.csv", false);
            for (int i = 0; i < customer_data.size(); i++) {
                writer.write(customer_data.get(i).getString() + "\n");
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
                if (peak_units.equals("") == false) {
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
