package Controller.authentication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Controller.schemas.Employee_Data;

public class Employee {
    public ArrayList<Employee_Data> employee_data = new ArrayList<Employee_Data>();

    public Employee() {
        load_data();
    }

    // read data from file and store in an array list of Employee schema
    public void load_data() {
        this.employee_data.clear();

        BufferedReader reader = null;
        FileReader f_reader = null;

        try {
            f_reader = new FileReader("storage/EmployeesData.csv");
            reader = new BufferedReader(f_reader);

            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String parts[] = line.split(",");
                String username = parts[0];
                String password = parts[1];

                Employee_Data data = new Employee_Data(username, password);
                this.employee_data.add(data);
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

    // write data to file after clearing it
    public void save_data() {
        FileWriter writer = null;

        try {
            writer = new FileWriter("storage/EmployeesData.csv", false);
            for (int i = 0; i < employee_data.size(); i++) {
                writer.write(employee_data.get(i).getString() + "\n");
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

    // check if employee already exists
    public boolean employee_exists(String username) {
        if (employee_data.size() == 0) {
            return false;
        }

        boolean flag = false;
        for (int i = 0; i < employee_data.size(); i++) {
            Employee_Data data = employee_data.get(i);
            if (data.username.equals(username)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    // signup an employee
    public boolean add_employee(String username, String password) {
        if (employee_exists(username) == true) {
            System.out.println("Error: Employee with Username already exists");
            return false;
        }
        Employee_Data new_data = new Employee_Data(username, password);
        employee_data.add(new_data);
        save_data();
        load_data();
        return true;
    }

    // change employee password
    public boolean change_password(String username, String password, String newPassword) {
        if (employee_data.size() == 0) {
            System.out.println("Error: Employee with this username doesn't exist");
            return false;
        }
        if (employee_exists(username) == false) {
            System.out.println("Error: Employee with this username doesn't exist");
            return false;
        }

        for (int i = 0; i < employee_data.size(); i++) {
            if (employee_data.get(i).username.equals(username) == true) {
                Employee_Data data = employee_data.get(i);
                if (data.password.equals(password) == false) {
                    System.out.println("Error: Invalid Username");
                    return false;
                } else if (data.password.equals(password) == true) {
                    String user = username;
                    String pass = newPassword;
                    employee_data.remove(i);
                    Employee_Data new_data = new Employee_Data(user, pass);
                    employee_data.add(new_data);
                }
                break;
            }
        }
        save_data();
        load_data();
        return true;
    }

    public boolean authenticate(String username, String password) {
        if (employee_data.size() == 0) {
            System.out.println("Error: Employee with this username doesn't exist");
            return false;
        }

        if (employee_exists(username) == false) {
            System.out.println("Error: Employee with this username doesn't exist");
            return false;
        }

        for (int i = 0; i < employee_data.size(); i++) {
            if (employee_data.get(i).username.equals(username) == true) {
                Employee_Data data = employee_data.get(i);
                if (data.password.equals(password) == false) {
                    System.out.println("Error: Invalid Password");
                    return false;
                } else if (data.password.equals(password) == true) {
                    return true;
                }
                break;
            }
        }
        return true;
    }
}