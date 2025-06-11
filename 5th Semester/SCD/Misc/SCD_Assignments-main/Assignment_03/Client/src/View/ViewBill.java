package View;

import javax.swing.*;

import Client.client;
import Controller.schemas.Billing_Data;
import java.util.*;

public class ViewBill extends JPanel {

    public JButton viewBillButton;
    public JLabel viewBillLabel;
    public JLabel customerIdLabel;
    public JTextField customerIdTextField;

    public ViewBill() {
        init();
    }

    private void init() {

        viewBillLabel = new javax.swing.JLabel();
        customerIdLabel = new javax.swing.JLabel();
        customerIdTextField = new javax.swing.JTextField();
        viewBillButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(730, 700));
        setMinimumSize(new java.awt.Dimension(730, 700));
        setLayout(null);

        viewBillLabel.setFont(new java.awt.Font("Arial", 1, 36));
        viewBillLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewBillLabel.setText("View Bill");
        viewBillLabel.setBounds(260, 90, 200, 50);
        add(viewBillLabel);

        customerIdLabel.setFont(new java.awt.Font("Arial", 1, 18));
        customerIdLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        customerIdLabel.setText("Enter Customer ID (4 Digit):");
        customerIdLabel.setBounds(240, 260, 250, 30);
        add(customerIdLabel);

        customerIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        customerIdTextField.setBounds(240, 320, 240, 30);
        add(customerIdTextField);

        viewBillButton.setFont(new java.awt.Font("Arial", 1, 18));
        viewBillButton.setText("View Bill");
        viewBillButton.setBounds(310, 480, 120, 30);
        viewBillButton.setFocusable(false);
        viewBillButton.addActionListener(new java.awt.event.ActionListener() {
            @SuppressWarnings("unchecked")
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String customerId = customerIdTextField.getText();
                if (customerId.length() != 4 || !customerId.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Customer ID must be 4 digits long and numbers only.",
                            "Invalid Customer ID",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Billing billings = new Billing();
                client cl = new client();
                ArrayList<Billing_Data> bill = (ArrayList<Billing_Data>) cl
                        .sendRequest("billings.get_bill," + customerId);
                if (bill.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No bills found for this customer ID.",
                            "No Bills Found",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                new ViewBillsFromCustID(customerId).setVisible(true);
            }
        });
        add(viewBillButton);
    }

}

class ViewBillsFromCustID extends javax.swing.JFrame {
    public JPanel mainPanel;
    public JScrollPane tableScrollPane;
    public JTable billsTable;

    public ViewBillsFromCustID(String customerId) {
        init(customerId);
    }

    @SuppressWarnings("unchecked")
    private void init(String customerId) {
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

        // Billing billing = new Billing();
        client cl = new client();
        ArrayList<Billing_Data> bills = (ArrayList<Billing_Data>) cl.sendRequest("billings.get_bill," + customerId);
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

        pack();
    }
}
