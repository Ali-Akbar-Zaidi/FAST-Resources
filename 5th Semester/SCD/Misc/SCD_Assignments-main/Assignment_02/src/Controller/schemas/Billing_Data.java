package Controller.schemas;

public class Billing_Data {
    public String cust_id = "";
    public String billing_month = "";
    public String billing_year = ""; // YYYY
    public String curr_meter_reading = ""; // regular
    public String curr_meter_reading_peak = ""; // peak
    public String reading_entry_date = ""; // in the format DD/MM/YYYY. cannot be in the future.
    public String electricity_cost = "";
    public String sales_tax = "";
    public String fixed_charges = "";
    public String total_billing_amount = "";
    public String due_date = ""; // in the format DD/MM/YYYY. (7 calendar days after the entry of Current Meter
                                 // reading)
    public String bill_status = ""; // Paid or Unpaid
    public String bill_payment_date = ""; // Bill payment Date in format DD/MM/YYYY (cannot be before Reading Entry
                                          // Date).

    public Billing_Data() {
        this.cust_id = "";
        this.billing_month = "";
        this.billing_year = "";
        this.curr_meter_reading = "";
        this.curr_meter_reading_peak = "";
        this.reading_entry_date = "";
        this.electricity_cost = "";
        this.sales_tax = "";
        this.fixed_charges = "";
        this.total_billing_amount = "";
        this.due_date = "";
        this.bill_status = "";
        this.bill_payment_date = "";
    }

    public Billing_Data(String cust_id, String billing_month, String billing_year, String curr_meter_reading,
            String curr_meter_reading_peak,
            String reading_entry_date, String electricity_cost, String sales_tax, String fixed_charges,
            String total_billing_amount, String due_date, String bill_status, String bill_payment_date) {
        this.cust_id = cust_id;
        this.billing_month = billing_month;
        this.billing_year = billing_year;
        this.curr_meter_reading = curr_meter_reading;
        this.curr_meter_reading_peak = curr_meter_reading_peak;
        this.reading_entry_date = reading_entry_date;
        this.electricity_cost = electricity_cost;
        this.sales_tax = sales_tax;
        this.fixed_charges = fixed_charges;
        this.total_billing_amount = total_billing_amount;
        this.due_date = due_date;
        this.bill_status = bill_status;
        this.bill_payment_date = bill_payment_date;
    }

    public String getString() {
        return this.cust_id + "," + this.billing_month + "," + this.billing_year + "," + this.curr_meter_reading + ","
                + this.curr_meter_reading_peak + "," + this.reading_entry_date + "," + this.electricity_cost + ","
                + this.sales_tax + "," + this.fixed_charges + "," + this.total_billing_amount + "," + this.due_date
                + "," + this.bill_payment_date + "," + this.bill_status;
    }
}
