package Controller.schemas;

public class Customer_Data {
    public String Unique_ID = ""; // 4 digit
    public String CNIC = ""; // 13 digit
    public String Name = "";
    public String Address = "";
    public String Phone_No = ""; // 11 digit
    public String cust_type = ""; // Commercial / Domestic
    public String meter_type = ""; // Single Phase / Three Phase
    public String reg_units_comsumed = "";
    public String peak_units_comsumed = ""; // blank for single phase

    public Customer_Data(String unique_id, String cnic, String name, String address, String phone_no, String cust_type,
            String meter_type, String peak_units_consumed, String reg_units_consumed) {
        this.Unique_ID = unique_id;
        this.CNIC = cnic;
        this.Name = name;
        this.Address = address;
        this.Phone_No = phone_no;
        this.cust_type = cust_type;
        this.meter_type = meter_type;
        this.reg_units_comsumed = reg_units_consumed;
        this.peak_units_comsumed = peak_units_consumed;
    }

    public Customer_Data() {
        this.Unique_ID = "";
        this.CNIC = "";
        this.Name = "";
        this.Address = "";
        this.Phone_No = "";
        this.cust_type = "";
        this.meter_type = "";
        this.reg_units_comsumed = "";
        this.peak_units_comsumed = "";
    }

    public String getString() {
        return this.Unique_ID + "," + this.CNIC + "," + this.Name + "," + this.Address + "," + this.Phone_No + ","
                + this.cust_type + "," + this.meter_type + "," + this.peak_units_comsumed + ","
                + this.reg_units_comsumed;
    }
}
