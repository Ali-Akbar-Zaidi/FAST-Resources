package View;

import javax.swing.*;

import Controller.authentication.Customer;
import Controller.billing.Billing;
import Controller.billing.TariffTax;
import Controller.schemas.Billing_Data;
import Controller.schemas.Customer_Data;

import java.awt.*;

public class CreateMonthsBill extends JPanel {
    JLabel title;
    JLabel message;
    JButton createBill;
    final String PATTERN = "dd/MM/yyyy";

    public CreateMonthsBill() {
        init();
    }

    public void init() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 0, 15, 0);

        title = new JLabel("Create This Month's Bill");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title, gbc);

        message = new JLabel("Click the button to create this month's bill");
        message.setFont(new Font("Arial", Font.PLAIN, 16));
        message.setHorizontalAlignment(JLabel.CENTER);
        add(message, gbc);

        createBill = new JButton("Create Bill");
        createBill.setFont(new Font("Arial", Font.PLAIN, 16));
        createBill.setFocusable(false);
        add(createBill, gbc);

        createBill.addActionListener(e -> {
            Billing billing = new Billing();
            String max_date = billing.getMaxDate();
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(PATTERN);
            java.time.LocalDate __today__ = java.time.LocalDate.now();
            String todays_date = __today__.format(formatter);
            String today[] = todays_date.split("/");
            String max[] = null;
            if (max_date != null) {
                max = max_date.split("/");
            }

            if (max_date != null && Integer.parseInt(max[1]) == Integer.parseInt(today[1])
                    && Integer.parseInt(max[2]) == Integer.parseInt(today[2])) {
                JOptionPane.showMessageDialog(null, "Error: Bills for this month already created");
                return;
            }

            Customer customer = new Customer();
            int count = 0;
            for (Customer_Data data : customer.customer_data) {
                count++;
                String cust_id = data.Unique_ID;
                String billing_month = today[1];
                String billing_year = today[2];
                String curr_meter_reading = randomNum();
                String curr_meter_peak = "";
                if (data.meter_type.equals("Three Phase")) {
                    curr_meter_peak = randomNum();
                }
                String reading_entry_date = todays_date;
                String electricity_cost = "";
                TariffTax tax = new TariffTax();
                int regular_unit_price = -1;
                int peak_unit_price = -1;
                int percent_of_tax = -1;
                int fixed_charges = -1;

                if (data.meter_type.equals("Single Phase")) {
                    if (data.cust_type.equals("Domestic")) {
                        regular_unit_price = Integer.parseInt(tax.domestic_1_phase.reg_unit_price);
                        percent_of_tax = Integer.parseInt(tax.domestic_1_phase.percent_of_Tax);
                        fixed_charges = Integer.parseInt(tax.domestic_1_phase.fixed_charges);
                        peak_unit_price = -1;
                    } else if (data.cust_type.equals("Commercial")) {
                        regular_unit_price = Integer.parseInt(tax.commercial_1_phase.reg_unit_price);
                        percent_of_tax = Integer.parseInt(tax.commercial_1_phase.percent_of_Tax);
                        fixed_charges = Integer.parseInt(tax.commercial_1_phase.fixed_charges);
                        peak_unit_price = -1;
                    }
                } else if (data.meter_type.equals("Three Phase")) {
                    if (data.cust_type.equals("Domestic")) {
                        regular_unit_price = Integer.parseInt(tax.domestic_3_phase.reg_unit_price);
                        peak_unit_price = Integer.parseInt(tax.domestic_3_phase.peak_unit_price);
                        percent_of_tax = Integer.parseInt(tax.domestic_3_phase.percent_of_Tax);
                        fixed_charges = Integer.parseInt(tax.domestic_3_phase.fixed_charges);
                    } else if (data.cust_type.equals("Commercial")) {
                        regular_unit_price = Integer.parseInt(tax.commercial_3_phase.reg_unit_price);
                        peak_unit_price = Integer.parseInt(tax.commercial_3_phase.peak_unit_price);
                        percent_of_tax = Integer.parseInt(tax.commercial_3_phase.percent_of_Tax);
                        fixed_charges = Integer.parseInt(tax.commercial_3_phase.fixed_charges);
                    }
                }
                int cost;
                cost = (Integer.parseInt(curr_meter_reading) * regular_unit_price);

                if (data.meter_type.equals("Three Phase")) {
                    cost = cost + (Integer.parseInt(curr_meter_peak) * peak_unit_price);
                }
                electricity_cost = String.valueOf(cost);
                String sales_tax = String.valueOf(percent_of_tax);
                String fix_charges = String.valueOf(fixed_charges);
                cost = cost + (int) ((percent_of_tax / 100.0) * cost);
                cost = cost + fixed_charges;
                String billing_amount = String.valueOf(cost);
                String due_date = weekLater();
                String bill_status = "Unpaid";
                String bill_payment_date = "";

                Billing_Data bill_data = new Billing_Data(cust_id, billing_month, billing_year,
                        curr_meter_reading, curr_meter_peak, reading_entry_date, electricity_cost, sales_tax,
                        fix_charges, billing_amount, due_date, bill_status, bill_payment_date);

                Billing bill = new Billing();
                bill.add(bill_data);
                bill.save_data();
                bill.load_data();
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Bill created successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error: No customers found");
            }
        });

        setVisible(true);
    }

    public String randomNum() {
        java.util.Random rand = new java.util.Random();
        int rn = rand.nextInt(10000);
        return Integer.toString(rn);
    }

    public String weekLater() {
        java.time.format.DateTimeFormatter frmt = java.time.format.DateTimeFormatter.ofPattern(PATTERN);
        java.time.LocalDate td = java.time.LocalDate.now(); // get todays date
        java.time.LocalDate fd = td.plusDays(7);
        return fd.format(frmt);
    }
}
