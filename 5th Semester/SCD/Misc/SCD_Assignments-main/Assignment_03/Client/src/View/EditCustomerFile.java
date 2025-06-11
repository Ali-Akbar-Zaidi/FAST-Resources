package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import Client.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Controller.schemas.Customer_Data;

public class EditCustomerFile extends JFrame {

    public JLabel searchLabel;
    public JPanel mainPanel;
    public JScrollPane tableScrollPane;
    public JTable taxTable;
    public JTextField searchTextField;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public EditCustomerFile() {
        init();
    }

    @SuppressWarnings("unchecked")
    private void init() {
        setIconImage(new Logo().getLogo());
        mainPanel = new JPanel();
        searchTextField = new JTextField();
        searchLabel = new JLabel();
        tableScrollPane = new JScrollPane();
        taxTable = new JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customer File");
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMaximumSize(new java.awt.Dimension(1000, 700));
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setPreferredSize(new java.awt.Dimension(1000, 700));
        getContentPane().setLayout(null);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(null);

        searchTextField.setFont(new java.awt.Font("Arial", 1, 18));
        searchTextField.setBounds(460, 40, 220, 30);
        mainPanel.add(searchTextField);

        searchLabel.setFont(new java.awt.Font("Arial", 1, 18));
        searchLabel.setText("Search:");
        searchLabel.setBounds(370, 40, 80, 30);
        mainPanel.add(searchLabel);

        client c = new client();
        ArrayList<Customer_Data> customer_data = (ArrayList<Customer_Data>) c.sendRequest("customer.customer_data");

        int size = customer_data.size();

        // Customer customer = new Customer();
        // int size = customer.customer_data.size();
        Object[][] data = new Object[size][11];

        for (int i = 0; i < size; i++) {
            data[i][0] = customer_data.get(i).Unique_ID;
            data[i][1] = customer_data.get(i).CNIC;
            data[i][2] = customer_data.get(i).Name;
            data[i][3] = customer_data.get(i).Address;
            data[i][4] = customer_data.get(i).Phone_No;
            data[i][5] = customer_data.get(i).cust_type;
            data[i][6] = customer_data.get(i).meter_type;
            data[i][7] = customer_data.get(i).reg_units_comsumed;
            data[i][8] = customer_data.get(i).peak_units_comsumed;
            data[i][9] = "Update";
            data[i][10] = "Delete";
        }

        DefaultTableModel model = new DefaultTableModel(
                data,
                new String[] { "Unique ID", "CNIC", "Name", "Address", "Phone", "Customer Type", "Meter Type",
                        "Regular Units", "Peak Units", "Update", "Delete" }) {
            boolean[] canEdit = new boolean[] { false, true, true, true, true, true, true, true, true, true, true };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if (columnIndex == 8 && getValueAt(rowIndex, 6).equals("Single Phase")) {
                    return false;
                }
                return canEdit[columnIndex];
            }
        };

        taxTable.setModel(model);
        rowSorter = new TableRowSorter<>(model);
        taxTable.setRowSorter(rowSorter);

        taxTable.setRowHeight(20);
        taxTable.setFillsViewportHeight(true);
        taxTable.getTableHeader().setReorderingAllowed(false);

        // Center text in cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < taxTable.getColumnCount(); i++) {
            taxTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Add Update button
        taxTable.getColumn("Update").setCellRenderer(new ButtonRenderer("Update"));
        taxTable.getColumn("Update").setCellEditor(new ButtonEditor(new JCheckBox(), "Update"));

        // Add Delete button
        taxTable.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete"));
        taxTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), "Delete"));

        tableScrollPane.setViewportView(taxTable);
        if (taxTable.getColumnModel().getColumnCount() > 0) {
            taxTable.getColumnModel().getColumn(0).setResizable(false);
            taxTable.getColumnModel().getColumn(1).setResizable(false);
            taxTable.getColumnModel().getColumn(2).setResizable(false);
            taxTable.getColumnModel().getColumn(3).setResizable(false);
        }

        taxTable.getColumnModel().getColumn(0).setResizable(false);

        tableScrollPane.setBounds(0, 100, 990, 560);
        mainPanel.add(tableScrollPane);

        getContentPane().add(mainPanel);
        mainPanel.setBounds(0, 0, 1000, 700);

        pack();

        // Add DocumentListener to searchTextField
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchTable();
            }

            private void searchTable() {
                String text = searchTextField.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        setVisible(true);

        if (size == 0) {
            JOptionPane.showMessageDialog(null, "No data found in the Customer file", "No Data",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer(String text) {
            setOpaque(true);
            setText(text);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, String buttonType) {
            super(checkBox);
            this.label = buttonType;
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            JButton button = new JButton((value == null) ? "" : value.toString());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                if (label.equals("Update")) {
                    // confirmation before updating
                    int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this record?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (response != JOptionPane.YES_OPTION) {
                        isPushed = false;
                        return new String(label);
                    }

                    String UniqueID = (String) taxTable.getValueAt(taxTable.getSelectedRow(), 0);
                    String CNIC = (String) taxTable.getValueAt(taxTable.getSelectedRow(), 1);
                    String Name = (String) taxTable.getValueAt(taxTable.getSelectedRow(), 2);
                    String Address = (String) taxTable.getValueAt(taxTable.getSelectedRow(), 3);
                    String Phone = (String) taxTable.getValueAt(taxTable.getSelectedRow(), 4);
                    String cust_type = (String) taxTable.getValueAt(taxTable.getSelectedRow(), 5);
                    String meter_type = (String) taxTable.getValueAt(taxTable.getSelectedRow(), 6);
                    String reg_units_comsumed = (String) taxTable.getValueAt(taxTable.getSelectedRow(), 7);
                    String peak_units_comsumed = (String) taxTable.getValueAt(taxTable.getSelectedRow(), 8);
                    int row = taxTable.getSelectedRow();

                    // Customer customer = new Customer();
                    if (CNIC.length() != 13 || !CNIC.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(null, "Invalid CNIC", "Error", JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                    client cl = new client();
                    boolean nadra_exists = (boolean) cl.sendRequest("nadra.exists," + CNIC);

                    // NADRA nadra = new NADRA();
                    // if (nadra.exists(CNIC) == false) {
                    if (nadra_exists == false) {
                        JOptionPane.showMessageDialog(null, "CNIC not found in NADRA. Enter a valid CNIC.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                    int cust_meters = (int) cl.sendRequest("customer.count_meters," + CNIC);

                    // if (customer.count_meters(CNIC) > 3) {
                    if (cust_meters > 3) {
                        JOptionPane.showMessageDialog(null,
                                "Customer can have at most 3 meters (per CNIC). Limit exceeded!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                    if (Name.length() < 7) {
                        JOptionPane.showMessageDialog(null, "Name must be at least 7 characters long", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                    if (Address.length() < 7 || Address.contains(",")) {
                        JOptionPane.showMessageDialog(null,
                                "Address must be at least 10 characters long (no commas allowed)", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                    if (Phone.length() != 11 || !Phone.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(null, "Invalid Phone Number (7 digits)", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                    if (cust_type.equals("Commercial") == false && cust_type.equals("Domestic") == false) {
                        JOptionPane.showMessageDialog(null, "Customer Type must be either Commercial or Domestic",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                    if (meter_type.equals("Single Phase") == false && meter_type.equals("Three Phase") == false) {
                        JOptionPane.showMessageDialog(null, "Meter Type must be either Single Phase or Three Phase",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                    if (Integer.parseInt(reg_units_comsumed) < 0 || !reg_units_comsumed.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(null, "Regular Units must be a number (greater then -1)", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                    if (meter_type.equals("Single Phase") && peak_units_comsumed.equals("") == false) {
                        peak_units_comsumed = "";
                    } else if (meter_type.equals("Three Phase") && peak_units_comsumed.equals("") == true) {
                        peak_units_comsumed = "0";
                    }

                    if (peak_units_comsumed.equals("") == false) {
                        if (Integer.parseInt(peak_units_comsumed) < -1 || !peak_units_comsumed.matches("[0-9]+")) {
                            JOptionPane.showMessageDialog(null, "Peak Units must be a number (greater then -1)",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            isPushed = false;
                            return new String(label);
                        }
                    }

                    taxTable.setValueAt(peak_units_comsumed, row, 8);
                    // Customer_Data temp_data = new Customer_Data(UniqueID, CNIC, Name, Address,
                    // Phone, cust_type,
                    // meter_type,
                    // peak_units_comsumed, reg_units_comsumed);
                    // boolean success = customer.replace_customer_data_by_unique_id(temp_data);
                    // customer.save_data();
                    // customer.load_data();
                    boolean success = (boolean) cl.sendRequest("customer.replace_customer_data_by_unique_id," + UniqueID
                            + ","
                            + CNIC + "," + Name + "," + Address + "," + Phone + "," + cust_type + "," + meter_type + ","
                            + peak_units_comsumed + "," + reg_units_comsumed);

                    if (success) {
                        JOptionPane.showMessageDialog(null, "Data updated successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    } else {
                        JOptionPane.showMessageDialog(null, "Data not updated", "Error", JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                } else if (label.equals("Delete")) {
                    // Ask for confirmation (i.e., are you sure)
                    int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (response != JOptionPane.YES_OPTION) {
                        isPushed = false;
                        return new String(label);
                    }

                    int row = taxTable.getSelectedRow();
                    System.out.println(row);
                    String UniqueID = (String) taxTable.getValueAt(row, 0);

                    // Customer customer = new Customer();
                    // boolean success = customer.delete_from_unique_id(UniqueID);
                    // customer.save_data();
                    // customer.load_data();

                    client cl = new client();
                    boolean success = (boolean) cl.sendRequest("customer.delete_from_unique_id," + UniqueID);

                    if (success) {
                        ((DefaultTableModel) taxTable.getModel()).removeRow(row);

                        JOptionPane.showMessageDialog(null, "Data deleted successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    } else {
                        JOptionPane.showMessageDialog(null, "Data not deleted", "Error", JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }
                }
            }
            isPushed = false;
            return new String(label);
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}