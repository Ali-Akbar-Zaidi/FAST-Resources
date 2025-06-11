package View;

import javax.swing.*;

import Client.client;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.awt.*;

public class NewMeter extends JPanel {
        public JRadioButton commercialRadioButton;
        public ButtonGroup customerTypeGroup;
        public JRadioButton domesticRadioButton;
        public ButtonGroup meterTypeGroup;
        public JRadioButton singlePhaseRadioButton;
        public JRadioButton threePhaseRadioButton;
        public JLabel titleLabel;
        public JLabel cnicLabel;
        public JLabel nameLabel;
        public JLabel addressLabel;
        public JLabel phoneLabel;
        public JLabel customerTypeLabel;
        public JLabel meterTypeLabel;
        public JTextField cnicTextField;
        public JTextField nameTextField;
        public JTextField addressTextField;
        public JTextField phoneTextField;
        public JToggleButton confirmToggleButton;

        public static boolean is_valid_date_format(String date) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                df.setLenient(false);
                try {
                        df.parse(date);
                        return true;
                } catch (Exception e) {
                        return false;
                }
        }

        public static boolean date_is_before_today(String date) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                try {
                        LocalDate inputDate = LocalDate.parse(date, formatter);
                        LocalDate today = LocalDate.now();

                        return !inputDate.isBefore(today);
                } catch (Exception e) {
                        System.out.println("Invalid date format: " + e.getMessage());
                        return false;
                }
        }

        public static String randomNum() {
                java.util.Random rand = new java.util.Random();
                int rn = rand.nextInt(10000);
                return Integer.toString(rn);
        }

        public NewMeter() {
                init();
        }

        private void init() {
                customerTypeGroup = new javax.swing.ButtonGroup();
                meterTypeGroup = new javax.swing.ButtonGroup();
                titleLabel = new javax.swing.JLabel();
                cnicLabel = new javax.swing.JLabel();
                cnicTextField = new javax.swing.JTextField();
                nameLabel = new javax.swing.JLabel();
                nameTextField = new javax.swing.JTextField();
                addressLabel = new javax.swing.JLabel();
                addressTextField = new javax.swing.JTextField();
                phoneLabel = new javax.swing.JLabel();
                phoneTextField = new javax.swing.JTextField();
                customerTypeLabel = new javax.swing.JLabel();
                commercialRadioButton = new javax.swing.JRadioButton();
                domesticRadioButton = new javax.swing.JRadioButton();
                meterTypeLabel = new javax.swing.JLabel();
                threePhaseRadioButton = new javax.swing.JRadioButton();
                singlePhaseRadioButton = new javax.swing.JRadioButton();
                confirmToggleButton = new javax.swing.JToggleButton();
                setBackground(Color.WHITE);

                domesticRadioButton.setBackground(Color.white);
                commercialRadioButton.setBackground(Color.white);
                singlePhaseRadioButton.setBackground(Color.white);
                threePhaseRadioButton.setBackground(Color.white);

                // centre text of all fields
                cnicTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                nameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                addressTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                phoneTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                titleLabel.setFont(new java.awt.Font("Arial", 1, 36));
                titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titleLabel.setText("Add new Meter");

                cnicLabel.setFont(new java.awt.Font("Arial", 1, 18));
                cnicLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                cnicLabel.setText("CNIC:");

                cnicTextField.setFont(new java.awt.Font("Arial", 1, 18));

                nameLabel.setFont(new java.awt.Font("Arial", 1, 18));
                nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                nameLabel.setText("Name:");

                nameTextField.setFont(new java.awt.Font("Arial", 1, 18));

                addressLabel.setFont(new java.awt.Font("Arial", 1, 18));
                addressLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                addressLabel.setText("Address:");

                addressTextField.setFont(new java.awt.Font("Arial", 1, 18));

                phoneLabel.setFont(new java.awt.Font("Arial", 1, 18));
                phoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                phoneLabel.setText("Phone:");

                phoneTextField.setFont(new java.awt.Font("Arial", 1, 18));

                customerTypeLabel.setFont(new java.awt.Font("Arial", 1, 18));
                customerTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                customerTypeLabel.setText("Customer Type:");

                customerTypeGroup.add(commercialRadioButton);
                commercialRadioButton.setFont(new java.awt.Font("Arial", 1, 18));
                commercialRadioButton.setText("Commercial");

                customerTypeGroup.add(domesticRadioButton);
                domesticRadioButton.setFont(new java.awt.Font("Arial", 1, 18));
                domesticRadioButton.setText("Domestic");
                domesticRadioButton.setSelected(true); // default selection

                meterTypeLabel.setFont(new java.awt.Font("Arial", 1, 18));
                meterTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                meterTypeLabel.setText("Meter Type:");

                meterTypeGroup.add(threePhaseRadioButton);
                threePhaseRadioButton.setFont(new java.awt.Font("Arial", 1, 18));
                threePhaseRadioButton.setText("Three Phase");

                meterTypeGroup.add(singlePhaseRadioButton);
                singlePhaseRadioButton.setFont(new java.awt.Font("Arial", 1, 18));
                singlePhaseRadioButton.setText("Single Phase");
                singlePhaseRadioButton.setSelected(true); // default selection

                confirmToggleButton.setFont(new java.awt.Font("Arial", 1, 18));
                confirmToggleButton.setText("Confirm");
                confirmToggleButton.setFocusable(false);

                confirmToggleButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                String CNIC = cnicTextField.getText();
                                String name = nameTextField.getText();
                                String address = addressTextField.getText();
                                String phone = phoneTextField.getText();
                                String customerType = commercialRadioButton.isSelected() ? "Commercial" : "Domestic";
                                String meterType = threePhaseRadioButton.isSelected() ? "Three Phase" : "Single Phase";

                                if (CNIC.length() != 13 || !CNIC.matches("[0-9]+")) {
                                        JOptionPane.showMessageDialog(null, "Invalid CNIC (13 digits)", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                        return;
                                }

                                if (name.length() < 7) {
                                        JOptionPane.showMessageDialog(null, "Name must be at least 7 characters long",
                                                        "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                        return;
                                }

                                if (address.length() < 7 || address.contains(",")) {
                                        JOptionPane.showMessageDialog(null,
                                                        "Invalid Address (minimum length 7 and no commas)", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                        return;
                                }

                                if (phone.length() != 11 || !phone.matches("[0-9]+")) {
                                        JOptionPane.showMessageDialog(null, "Invalid Phone (11 digits required)",
                                                        "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                        return;
                                }

                                String reg_units = "0";
                                String peak_units = "";

                                if (meterType.equals("Three Phase")) {
                                        peak_units = "0";
                                }

                                String issue_date = "";
                                String expiry_date = "";

                                // NADRA nadra = new NADRA();

                                client cl = new client();
                                boolean nadra_exists = (boolean) cl.sendRequest("nadra.exists," + CNIC);

                                // if (!nadra.exists(CNIC)) {
                                if (!nadra_exists) {
                                        issue_date = (String) JOptionPane.showInputDialog(null,
                                                        "Enter issue date (DD/MM/YYYY):", "Input",
                                                        JOptionPane.PLAIN_MESSAGE, null, null, "");
                                        while (issue_date.length() != 10 || !is_valid_date_format(issue_date)
                                                        || date_is_before_today(issue_date)) {
                                                issue_date = (String) JOptionPane.showInputDialog(null,
                                                                "Error! Enter valid issue date (DD/MM/YYYY):", "Input",
                                                                JOptionPane.PLAIN_MESSAGE, null, null, "");
                                        }

                                        expiry_date = (String) JOptionPane.showInputDialog(null,
                                                        "Enter expiry date (DD/MM/YYYY):", "Input",
                                                        JOptionPane.PLAIN_MESSAGE, null, null, "");
                                        while (expiry_date.length() != 10 || !is_valid_date_format(expiry_date)
                                                        || !date_is_before_today(expiry_date)) {
                                                expiry_date = (String) JOptionPane.showInputDialog(null,
                                                                "Error! Enter valid expiry date (DD/MM/YYYY):", "Input",
                                                                JOptionPane.PLAIN_MESSAGE, null, null, "");
                                        }
                                }

                                // nadra.add(CNIC, issue_date, expiry_date);
                                boolean added = (boolean) cl.sendRequest(
                                                "nadra.add," + CNIC + "," + issue_date + "," + expiry_date);

                                // Customer cust = new Customer();

                                // int count = cust.count_meters(CNIC);

                                int count = (int) cl.sendRequest("cust.count_meters," + CNIC);

                                String unique_id = randomNum();

                                while ((boolean) cl.sendRequest("customer.isUnique," + CNIC) == false) {
                                        unique_id = randomNum();
                                }

                                while (unique_id.length() < 4) {
                                        unique_id = "0" + unique_id;
                                }

                                if (count < 3) // add the new one
                                {
                                        // boolean bool = cust.add_meter(unique_id, CNIC, name, address, phone,
                                        // customerType, meterType,
                                        // peak_units,
                                        // reg_units);

                                        boolean bool = (boolean) cl.sendRequest("cust.add_meter," + unique_id + ","
                                                        + CNIC + ","
                                                        + name + ","
                                                        + address
                                                        + "," + phone + "," + customerType + "," + meterType + ","
                                                        + peak_units
                                                        + "," + reg_units);

                                        // cust.save_data();
                                        // cust.load_data();

                                        if (bool == true) {
                                                JOptionPane.showMessageDialog(null, "Meter added successfully",
                                                                "Success",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                        }
                                } else {
                                        JOptionPane.showMessageDialog(null, "Customer already has 3 meters", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                        return;
                                }

                                cnicTextField.setText("");
                                nameTextField.setText("");
                                addressTextField.setText("");
                                phoneTextField.setText("");
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
                                this);
                this.setLayout(layout);
                layout.setHorizontalGroup(layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                                                .createSequentialGroup().addContainerGap(194, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout
                                                                                .createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(cnicLabel)
                                                                                                .addComponent(nameLabel)
                                                                                                .addComponent(addressLabel)
                                                                                                .addComponent(phoneLabel))
                                                                                .addGap(60, 60, 60)
                                                                                .addGroup(layout.createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                                .addComponent(cnicTextField,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                247,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(nameTextField)
                                                                                                .addComponent(addressTextField)
                                                                                                .addComponent(phoneTextField)))
                                                                .addGroup(layout.createSequentialGroup()
                                                                                .addGap(20, 20, 20)
                                                                                .addComponent(titleLabel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                285,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(layout.createSequentialGroup()
                                                                                .addGap(10, 10, 10)
                                                                                .addGroup(layout.createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(customerTypeLabel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                285,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                .addGap(10, 10, 10)
                                                                                                                .addComponent(domesticRadioButton)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(commercialRadioButton))
                                                                                                .addComponent(meterTypeLabel,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                285,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                .addGap(10, 10, 10)
                                                                                                                .addComponent(singlePhaseRadioButton)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(threePhaseRadioButton)))))
                                                .addContainerGap(174, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap(271, Short.MAX_VALUE).addComponent(confirmToggleButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 172,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(271, Short.MAX_VALUE)));
                layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout
                                                .createSequentialGroup().addGap(39, 39, 39)
                                                .addComponent(
                                                                titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(cnicLabel)
                                                                .addComponent(cnicTextField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(nameLabel)
                                                                .addComponent(nameTextField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(addressLabel)
                                                                .addComponent(addressTextField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(phoneLabel).addComponent(phoneTextField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18).addComponent(customerTypeLabel).addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(domesticRadioButton)
                                                                .addComponent(commercialRadioButton))
                                                .addGap(18, 18, 18).addComponent(meterTypeLabel).addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(singlePhaseRadioButton)
                                                                .addComponent(threePhaseRadioButton))
                                                .addGap(74, 74, 74)
                                                .addComponent(confirmToggleButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 48,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(154, Short.MAX_VALUE)));
        }

}
