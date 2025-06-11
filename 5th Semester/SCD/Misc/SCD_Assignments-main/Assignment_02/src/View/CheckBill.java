package View;

import javax.swing.*;

import Controller.billing.Billing;
import Controller.schemas.Billing_Data;

import java.util.ArrayList;

public class CheckBill extends javax.swing.JPanel {
    public JButton confirmButton;
    public JLabel titleLabel;
    public JLabel cnicLabel;
    public JLabel meterTypeLabel;
    public JLabel customerIdLabel;
    public JTextField cnicTextField;
    public JTextField meterTypeTextField;
    public JTextField customerIdTextField;

    public CheckBill() {
        init();
    }

    private void init() {

        titleLabel = new javax.swing.JLabel();
        cnicLabel = new javax.swing.JLabel();
        cnicTextField = new javax.swing.JTextField();
        meterTypeLabel = new javax.swing.JLabel();
        meterTypeTextField = new javax.swing.JTextField();
        confirmButton = new javax.swing.JButton();
        customerIdLabel = new javax.swing.JLabel();
        customerIdTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(730, 700));
        setMinimumSize(new java.awt.Dimension(730, 700));
        setLayout(null);

        titleLabel.setFont(new java.awt.Font("Arial", 1, 36));
        titleLabel.setText("Check Bill");
        titleLabel.setBounds(280, 100, 200, 40);
        add(titleLabel);

        cnicLabel.setFont(new java.awt.Font("Arial", 1, 18));
        cnicLabel.setText("CNIC:");
        cnicLabel.setBounds(200, 260, 60, 30);
        add(cnicLabel);

        cnicTextField.setBounds(280, 260, 250, 30);
        add(cnicTextField);

        meterTypeLabel.setFont(new java.awt.Font("Arial", 1, 18));
        meterTypeLabel.setText("Meter Type:");
        meterTypeLabel.setBounds(200, 380, 120, 30);
        add(meterTypeLabel);

        meterTypeTextField.setBounds(340, 380, 190, 30);
        add(meterTypeTextField);

        confirmButton.setFont(new java.awt.Font("Arial", 1, 24));
        confirmButton.setText("Confirm");
        confirmButton.setBounds(310, 510, 150, 40);

        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String CNIC = cnicTextField.getText();
                String customerId = customerIdTextField.getText();
                String MeterType = meterTypeTextField.getText();
                if (CNIC.length() != 13 || CNIC.matches("[0-9]+") == false) {
                    JOptionPane.showMessageDialog(null, "Invalid CNIC", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (customerId.length() != 4 || customerId.matches("[0-9]+") == false) {
                    JOptionPane.showMessageDialog(null, "Invalid Customer ID", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (MeterType.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid Meter Type", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (MeterType.equals("Single Phase") == false && MeterType.equals("Three Phase") == false) {
                    JOptionPane.showMessageDialog(null, "Invalid Meter Type", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                new CheckBillPopOver(CNIC, customerId, MeterType);
                // clear the fields
                cnicTextField.setText("");
                customerIdTextField.setText("");
                meterTypeTextField.setText("");
            }
        });
        add(confirmButton);

        customerIdLabel.setFont(new java.awt.Font("Arial", 1, 18));
        customerIdLabel.setText("Customer ID:");
        customerIdLabel.setBounds(200, 320, 120, 30);
        add(customerIdLabel);

        customerIdTextField.setBounds(340, 320, 190, 30);
        add(customerIdTextField);

        setVisible(true);
    }
}

class CheckBillPopOver extends javax.swing.JFrame {
    public JPanel mainPanel;
    public JScrollPane tableScrollPane;
    public JTable billsTable;

    public CheckBillPopOver(String CNIC, String customerId, String MeterType) {
        init(CNIC, customerId, MeterType);
    }

    private void init(String CNIC, String customerId, String MeterType) {
        setTitle("Bills for Customer ID: " + customerId);
        setIconImage(new Logo().getLogo());
        mainPanel = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        billsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 700));
        setMinimumSize(new java.awt.Dimension(1000, 700));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(null);

        Billing billing = new Billing();
        ArrayList<Billing_Data> bills = billing.get_bill(customerId);
        int size = bills.size();
        System.out.println(size);
        Object[][] data = new Object[size][13];
        for (int i = 0; i < size; i++) {
            Billing_Data bill = bills.get(i);
            data[i][0] = bill.cust_id;
            data[i][1] = bill.billing_month;
            data[i][2] = bill.billing_year;
            data[i][3] = bill.curr_meter_reading;
            data[i][4] = bill.curr_meter_reading_peak;
            data[i][5] = bill.reading_entry_date;
            data[i][6] = bill.electricity_cost;
            data[i][7] = bill.sales_tax;
            data[i][8] = bill.fixed_charges;
            data[i][9] = bill.total_billing_amount;
            data[i][10] = bill.due_date;
            data[i][11] = bill.bill_status;
            data[i][12] = bill.bill_payment_date;
        }
        billsTable.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[] {
                        "Customer ID", "Billing Month", "Billing Year", "Current Reading (Reg)",
                        "Current Reading (Peak)", "Entry Date", "Electricity Cost", "Sales Tax", "Fixed Charges",
                        "Total Billing Amount", "Due Date", "Bill Status", "Bill Payment Date"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        billsTable.getTableHeader().setReorderingAllowed(false);
        tableScrollPane.setViewportView(billsTable);

        tableScrollPane.setBounds(0, 0, 1000, 660);
        mainPanel.add(tableScrollPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE));

        // pack();

        // set visible true
        setVisible(true);

        if (size == 0) {
            JOptionPane.showMessageDialog(null,
                    "No bills found for Customer ID: " + customerId + ", under the following criteria.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
        }
    }
}
