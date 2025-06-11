package View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.authentication.Customer;
import Controller.authentication.NADRA;
import Controller.schemas.Customer_Data;
import Controller.schemas.NADRA_Data;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SeeExpiredCNIC extends JPanel {
    public JLabel expiredCNICsLabel;
    public JScrollPane tableScrollPane;
    public JTable expiredCNICsTable;

    public static int checkCNICExpiry(String expiryDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expirationDate = LocalDate.parse(expiryDate, formatter);
        LocalDate currentDate = LocalDate.now();

        if (currentDate.isAfter(expirationDate)) {
            return -1; // expired
        }

        int daysUntilExpiry = (int) ChronoUnit.DAYS.between(currentDate, expirationDate);

        if (daysUntilExpiry <= 30) {
            return 1; // close to expiry
        }

        return 0; // not close to expiry
    }

    public SeeExpiredCNIC() {
        init();
    }

    private void init() {
        setBackground(Color.WHITE);
        expiredCNICsLabel = new JLabel();
        tableScrollPane = new JScrollPane();
        expiredCNICsTable = new JTable();

        setMaximumSize(new Dimension(730, 700));
        setMinimumSize(new Dimension(730, 700));
        setBounds(0, 0, 730, 700);
        setLayout(null);

        expiredCNICsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        expiredCNICsLabel.setText("Expired CNIC's");
        expiredCNICsLabel.setBounds(270, 30, 200, 30);
        add(expiredCNICsLabel);

        NADRA nadra = new NADRA();
        int dataSize = nadra.nadra_data.size();
        Object[][] data = new Object[dataSize][5]; // dataSize rows, 5 columns
        int index = 0;

        int count = 0;

        if (nadra.nadra_data.size() > 0) {
            for (NADRA_Data nadraData : nadra.nadra_data) {
                int result = checkCNICExpiry(nadraData.expiry_date);
                String status = "";
                boolean flag = false;
                if (result == -1) {
                    status = "Expired";
                    flag = true;
                } else if (result == 1) {
                    status = "Close to Expiry";
                    flag = true;
                }

                if (flag) {
                    count++;
                    Customer customer = new Customer();
                    Customer_Data temp_customer = customer.getCustomer(nadraData.CNIC);
                    if (temp_customer != null) {
                        data[index][0] = temp_customer.Name;
                        data[index][1] = nadraData.CNIC;
                        data[index][2] = temp_customer.Unique_ID;
                        data[index][3] = nadraData.expiry_date;
                        data[index][4] = status;
                        index++;
                    } else {
                        // failed to fetch data
                        JOptionPane.showMessageDialog(null, "Failed to fetch data for CNIC: " + nadraData.CNIC,
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        // Fill remaining rows with null if less than dataSize elements
        for (int i = index; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = null;
            }
        }

        DefaultTableModel model = new DefaultTableModel(
                data,
                new String[] {
                        "Name", "CNIC", "Unique ID", "Expiry Date", "Status"
                }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        expiredCNICsTable.setModel(model);

        // Center text in table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < expiredCNICsTable.getColumnCount(); i++) {
            expiredCNICsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Disable column reordering
        expiredCNICsTable.getTableHeader().setReorderingAllowed(false);
        expiredCNICsTable.setFillsViewportHeight(true);

        tableScrollPane.setViewportView(expiredCNICsTable);
        tableScrollPane.setBounds(0, 80, 730, 580);

        add(tableScrollPane);

        setVisible(true);

        if (count == 0) {
            JOptionPane.showMessageDialog(null, "No expired CNIC's found", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
