package billing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import schemas.Tariff_Data;

public class TariffTax {

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
        BufferedReader reader = null;
        FileReader f_reader = null;

        try {
            f_reader = new FileReader("storage/TariffTaxInfo.csv");
            reader = new BufferedReader(f_reader);

            String line;

            if ((line = reader.readLine()) != null) {
                line = line.trim();
                String parts[] = line.split(",");

                domestic_1_phase = new Tariff_Data(parts[0], parts[1], parts[2], parts[3], parts[4]);
            }

            if ((line = reader.readLine()) != null) {
                line = line.trim();
                String parts[] = line.split(",");

                commercial_1_phase = new Tariff_Data(parts[0], parts[1], parts[2], parts[3], parts[4]);
            }

            if ((line = reader.readLine()) != null) {
                line = line.trim();
                String parts[] = line.split(",");

                domestic_3_phase = new Tariff_Data(parts[0], parts[1], parts[2], parts[3], parts[4]);
            }

            if ((line = reader.readLine()) != null) {
                line = line.trim();
                String parts[] = line.split(",");

                commercial_3_phase = new Tariff_Data(parts[0], parts[1], parts[2], parts[3], parts[4]);
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
            writer = new FileWriter("storage/TariffTaxInfo.csv", false);

            writer.write(domestic_1_phase.getString() + "\n");
            writer.write(commercial_1_phase.getString() + "\n");
            writer.write(domestic_3_phase.getString() + "\n");
            writer.write(commercial_3_phase.getString() + "\n");

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
}
