package View;

import javax.swing.*;

import Client.client;
import Controller.schemas.Customer_Data;
import Controller.schemas.Tariff_Data;

import java.util.*;

import java.awt.*;

public class CreateMonthsBill extends JPanel {
    JLabel title;
    JLabel message;
    JButton createBill;
    final static String PATTERN = "dd/MM/yyyy";

    public CreateMonthsBill() {
        init();
    }

    @SuppressWarnings("unchecked")
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
            // Billing billing = new Billing();
            // String max_date = billing.getMaxDate();
            client cl = new client();
            String max_date = (String) cl.sendRequest("billing.getMaxDate");
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

            ArrayList<Customer_Data> customers = (ArrayList<Customer_Data>) cl.sendRequest("customer.customer_data");
            int count = 0;
            for (Customer_Data data : customers) {
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
                // TariffTax tax = new TariffTax();
                client cl1 = new client();
                ArrayList<Tariff_Data> _tax = (ArrayList<Tariff_Data>) cl1.sendRequest("tarriftax.tarrif_data");
                Tariff_Data d1 = _tax.get(0);
                Tariff_Data c1 = _tax.get(1);
                Tariff_Data d3 = _tax.get(2);
                Tariff_Data c3 = _tax.get(3);
                int regular_unit_price = -1;
                int peak_unit_price = 1;
                int percent_of_tax = -1;
                int fixed_charges = -1;

                if (data.meter_type.equals("Single Phase")) {
                    if (data.cust_type.equals("Domestic")) {
                        regular_unit_price = Integer.parseInt(d1.reg_unit_price);
                        percent_of_tax = Integer.parseInt(d1.percent_of_Tax);
                        fixed_charges = Integer.parseInt(d1.fixed_charges);
                        peak_unit_price = 1;
                    } else if (data.cust_type.equals("Commercial")) {
                        regular_unit_price = Integer.parseInt(c1.reg_unit_price);
                        percent_of_tax = Integer.parseInt(c1.percent_of_Tax);
                        fixed_charges = Integer.parseInt(c1.fixed_charges);
                        peak_unit_price = 1;
                    }
                } else if (data.meter_type.equals("Three Phase")) {
                    if (data.cust_type.equals("Domestic")) {
                        regular_unit_price = Integer.parseInt(d3.reg_unit_price);
                        peak_unit_price = Integer.parseInt(d3.peak_unit_price);
                        percent_of_tax = Integer.parseInt(d3.percent_of_Tax);
                        fixed_charges = Integer.parseInt(d3.fixed_charges);
                    } else if (data.cust_type.equals("Commercial")) {
                        regular_unit_price = Integer.parseInt(c3.reg_unit_price);
                        peak_unit_price = Integer.parseInt(c3.peak_unit_price);
                        percent_of_tax = Integer.parseInt(c3.percent_of_Tax);
                        fixed_charges = Integer.parseInt(c3.fixed_charges);
                    }
                }
                System.out.println("Regular Unit Price: " + regular_unit_price);
                System.out.println("Peak Unit Price: " + peak_unit_price);
                System.out.println("Percent of Tax: " + percent_of_tax);
                System.out.println("Fixed Charges: " + fixed_charges);
                int cost;
                cost = (Integer.parseInt(curr_meter_reading) * regular_unit_price);
                System.out.println("Cost: " + cost);

                if (data.meter_type.equals("Three Phase")) {
                    cost = cost + (Integer.parseInt(curr_meter_peak) * peak_unit_price);
                }
                System.out.println("Cost: " + cost);
                electricity_cost = String.valueOf(cost);
                System.out.println("Electricity Cost: " + cost);
                String sales_tax = String.valueOf(percent_of_tax);
                System.out.println("Sales Tax: " + percent_of_tax);
                String fix_charges = String.valueOf(fixed_charges);
                System.out.println("Fixed Charges: " + fixed_charges);
                cost = cost + (int) ((percent_of_tax / 100.0) * cost);
                cost = cost + fixed_charges;
                System.out.println("Cost: " + cost);
                String billing_amount = String.valueOf(cost);
                System.out.println("Billing Amount: " + cost);
                String due_date = weekLater();
                String bill_status = "Unpaid";
                String bill_payment_date = "";

                if (cust_id.equals("")) {
                    cust_id = "NULL";
                }

                if (billing_month.equals("")) {
                    billing_month = "NULL";
                }

                if (billing_year.equals("")) {
                    billing_year = "NULL";
                }

                if (curr_meter_reading.equals("")) {
                    curr_meter_reading = "NULL";
                }

                if (curr_meter_peak.equals("")) {
                    curr_meter_peak = "NULL";
                }

                if (reading_entry_date.equals("")) {
                    reading_entry_date = "NULL";
                }

                if (electricity_cost.equals("")) {
                    electricity_cost = "NULL";
                }

                if (sales_tax.equals("")) {
                    sales_tax = "NULL";
                }

                if (fix_charges.equals("")) {
                    fix_charges = "NULL";
                }

                if (billing_amount.equals("")) {
                    billing_amount = "NULL";
                }

                if (due_date.equals("")) {
                    due_date = "NULL";
                }

                if (bill_status.equals("")) {
                    bill_status = "NULL";
                }

                if (bill_payment_date.equals("")) {
                    bill_payment_date = "NULL";
                }

                String req = "billing.add," + cust_id + "," + billing_month + "," + billing_year + ","
                        + curr_meter_reading
                        + "," + curr_meter_peak + "," + reading_entry_date + "," + electricity_cost + "," + sales_tax
                        + ","
                        + fix_charges + "," + billing_amount + "," + due_date + "," + bill_status + ","
                        + bill_payment_date;

                // Billing_Data bill_data = new Billing_Data(cust_id, billing_month,
                // billing_year, curr_meter_reading, curr_meter_peak, reading_entry_date,
                // electricity_cost, sales_tax, fix_charges, billing_amount, due_date,
                // bill_status, bill_payment_date);

                // Billing bill = new Billing();
                // bill.add(bill_data);
                // bill.save_data();
                // bill.load_data();

                boolean flag = (boolean) cl.sendRequest(req);

                if (!flag) {
                    JOptionPane.showMessageDialog(null, "Error: Bill not created for customer " + cust_id);
                }
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Bill created successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error: No customers found");
            }
        });

        setVisible(true);
    }

    public static String randomNum() {
        java.util.Random rand = new java.util.Random();
        int rn = rand.nextInt(10000);
        return Integer.toString(rn);
    }

    public static String weekLater() {
        java.time.format.DateTimeFormatter frmt = java.time.format.DateTimeFormatter.ofPattern(PATTERN);
        java.time.LocalDate td = java.time.LocalDate.now(); // get todays date
        java.time.LocalDate fd = td.plusDays(7);
        return fd.format(frmt);
    }
}
