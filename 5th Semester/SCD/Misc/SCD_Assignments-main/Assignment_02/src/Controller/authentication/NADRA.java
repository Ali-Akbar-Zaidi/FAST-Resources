package Controller.authentication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Controller.schemas.NADRA_Data;

public class NADRA {
    public ArrayList<NADRA_Data> nadra_data = new ArrayList<NADRA_Data>();

    public NADRA() {
        load_data();
    }

    public void load_data() {
        this.nadra_data.clear();

        BufferedReader reader = null;
        FileReader f_reader = null;

        try {
            f_reader = new FileReader("storage/NADRADB.csv");
            reader = new BufferedReader(f_reader);

            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String parts[] = line.split(",");

                NADRA_Data data = new NADRA_Data(parts[0], parts[1], parts[2]);
                this.nadra_data.add(data);
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

    public void save_data() {
        FileWriter writer = null;

        try {
            writer = new FileWriter("storage/NADRADB.csv", false);
            for (int i = 0; i < this.nadra_data.size(); i++) {
                writer.write(this.nadra_data.get(i).getString() + "\n");
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

    public boolean cnic_exits_except_index(String CNIC, int index) {
        for (int i = 0; i < this.nadra_data.size(); i++) {
            if (i == index) {
                continue;
            }
            if (this.nadra_data.get(i).CNIC.equals(CNIC)) {
                return true;
            }
        }
        return false;
    }

    public boolean exists(String CNIC) {
        if (this.nadra_data.size() == 0) {
            return false;
        }

        for (int i = 0; i < this.nadra_data.size(); i++) {
            if (this.nadra_data.get(i).CNIC.equals(CNIC)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(String CNIC, String issue_date, String expiry_date) {
        if (exists(CNIC) == true) {
            System.out.println("Error: CNIC already exists");
            return false;
        }

        NADRA_Data data = new NADRA_Data(CNIC, issue_date, expiry_date);
        this.nadra_data.add(data);
        this.save_data();
        this.load_data();
        return true;
    }

    public boolean update(String CNIC, String issue_date, String expiry_date) {
        if (exists(CNIC) == false) {
            System.out.println("Error: CNIC doesn't exist");
            return false;
        }

        for (int i = 0; i < this.nadra_data.size(); i++) {
            if (nadra_data.get(i).CNIC.equals(CNIC)) {
                NADRA_Data new_data = new NADRA_Data(CNIC, issue_date, expiry_date);
                this.nadra_data.set(i, new_data);
                this.save_data();
                this.load_data();
                return true;
            }
        }
        return false;
    }
}
