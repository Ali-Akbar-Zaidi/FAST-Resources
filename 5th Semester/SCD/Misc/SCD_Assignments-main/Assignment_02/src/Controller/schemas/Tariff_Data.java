package Controller.schemas;

public class Tariff_Data {
    public String Meter_Type = "";
    public String reg_unit_price = "";
    public String peak_unit_price = ""; // not applicable in 1 phase customers
    public String percent_of_Tax = "";
    public String fixed_charges = "";

    public Tariff_Data() {
        this.Meter_Type = "";
        this.reg_unit_price = "";
        this.peak_unit_price = "";
        this.percent_of_Tax = "";
        this.fixed_charges = "";
    }

    public Tariff_Data(String Meter_Type, String reg_unit_price, String peak_unit_price, String percent_of_Tax,
            String fixed_charges) {
        this.Meter_Type = Meter_Type;
        this.reg_unit_price = reg_unit_price;
        this.peak_unit_price = peak_unit_price;
        this.percent_of_Tax = percent_of_Tax;
        this.fixed_charges = fixed_charges;
    }

    public String getString() {
        return this.Meter_Type + "," + this.reg_unit_price + "," + this.peak_unit_price + "," + this.percent_of_Tax
                + "," + this.fixed_charges;
    }
}
