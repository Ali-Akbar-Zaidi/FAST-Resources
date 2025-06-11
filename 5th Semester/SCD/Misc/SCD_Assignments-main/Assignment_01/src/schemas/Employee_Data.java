package schemas;

public class Employee_Data {
    public String username = "";
    public String password = "";

    public Employee_Data(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employee_Data() {
        this.password = "";
        this.username = "";
    }

    public String getString() {
        return this.username + "," + this.password;
    }
}
