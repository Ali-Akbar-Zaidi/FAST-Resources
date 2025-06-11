package Controller.schemas;

public class NADRA_Data {
    public String CNIC = ""; // 13 digits
    public String issue_date = ""; // format = DD/MM/YYYY
    public String expiry_date = ""; // format = DD/MM/YYYY

    public NADRA_Data() {
        this.CNIC = "";
        this.issue_date = "";
        this.expiry_date = "";
    }

    public NADRA_Data(String CNIC, String issue_date, String expiry_date) {
        this.CNIC = CNIC;
        this.issue_date = issue_date;
        this.expiry_date = expiry_date;
    }

    public String getString() {
        return this.CNIC + "," + this.issue_date + "," + this.expiry_date;
    }
}
