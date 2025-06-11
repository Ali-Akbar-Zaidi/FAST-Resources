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

import Controller.schemas.Billing_Data;

public class EditBillingFile extends JFrame {
    public JLabel searchLabel;
    public JPanel mainPanel;
    public JScrollPane tableScrollPane;
    public JTable taxTable;
    public JTextField searchTextField;
    private TableRowSorter<DefaultTableModel> rowSorter;
    final static String FORMAT = "dd/MM/yyyy";

    public EditBillingFile() {
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
        setTitle("Billing Data File");
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

        client cl = new client();
        ArrayList<Billing_Data> billing_data = (ArrayList<Billing_Data>) cl.sendRequest("billing.billing_data");

        // Billing billing = new Billing();
        int size = billing_data.size();
        Object[][] data = new Object[size][15];

        for (int i = 0; i < size; i++) {
            data[i][0] = billing_data.get(i).cust_id;
            data[i][1] = billing_data.get(i).billing_month;
            data[i][2] = billing_data.get(i).billing_year;
            data[i][3] = billing_data.get(i).curr_meter_reading;
            data[i][4] = billing_data.get(i).curr_meter_reading_peak;
            data[i][5] = billing_data.get(i).reading_entry_date;
            data[i][6] = billing_data.get(i).electricity_cost;
            data[i][7] = billing_data.get(i).sales_tax;
            data[i][8] = billing_data.get(i).fixed_charges;
            data[i][9] = billing_data.get(i).total_billing_amount;
            data[i][10] = billing_data.get(i).due_date;
            data[i][11] = billing_data.get(i).bill_status;
            data[i][12] = billing_data.get(i).bill_payment_date;
            data[i][13] = "Update";
            data[i][13] = "Delete";
        }

        DefaultTableModel model = new DefaultTableModel(
                data,
                new String[] { "Customer ID", "Billing Month", "Billing Year", "Current Reading (Reg)",
                        "Current Reading (Peak)",
                        "Entry Date",
                        "Electricity Cost", "Sales Tax", "Fixed Charges", "Total Billing Amount", "Due Date",
                        "Bill Status", "Payment Date", "Update", "Delete" }) {
            boolean[] canEdit = new boolean[] { false, false, false, true, true, false, true, true, true, true, true,
                    false,
                    false, true, true };
            client cl = new client();
            String latest_date = (String) cl.sendRequest("Billing.get_latest_date");

            public boolean isCellEditable(int rowIndex, int columnIndex) {

                if (getValueAt(rowIndex, 5).equals(latest_date) == false) {

                    return false;
                }

                if (columnIndex == 4 && getValueAt(rowIndex, 4).equals("")) {
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

        // resizable false
        taxTable.getTableHeader().setResizingAllowed(false);

        // customer cell width to allow horizontal scrolling
        taxTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(7).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(8).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(9).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(10).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(11).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(12).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(13).setPreferredWidth(100);
        taxTable.getColumnModel().getColumn(14).setPreferredWidth(100);
        // disable auto resizing
        taxTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
            JOptionPane.showMessageDialog(null, "No data found in the Billing file", "No Data",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer(String text) {
            setOpaque(true);
            setText(text);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            String EntryDate = table.getValueAt(row, 5).toString();
            client cl = new client();
            String latest_date = (String) cl.sendRequest("Billing.get_latest_date");
            if (EntryDate.equals(latest_date) == false) {
                setEnabled(false);
            } else {
                setEnabled(true);
            }
            return this;
        }
    }

    public class ButtonEditor extends DefaultCellEditor {
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, String buttonType) {
            super(checkBox);
            this.label = buttonType;
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
            isPushed = true;
            return button;
        }

        public static boolean is_valid_date_format(String dt) {
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(FORMAT);
            df.setLenient(false);
            try {
                df.parse(dt);
                return true;
            } catch (Exception exception) {
                return false;
            }
        }

        public static boolean first_is_after_second(String first, String second) {
            String[] first_date = first.split("/");
            String[] second_date = second.split("/");
            if (Integer.parseInt(first_date[2]) > Integer.parseInt(second_date[2])) {
                return true;
            } else if (Integer.parseInt(first_date[2]) == Integer.parseInt(second_date[2])) {
                if (Integer.parseInt(first_date[1]) > Integer.parseInt(second_date[1])) {
                    return true;
                } else if (Integer.parseInt(first_date[1]) == Integer.parseInt(second_date[1])) {
                    if (Integer.parseInt(first_date[0]) > Integer.parseInt(second_date[0])) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static boolean first_is_equal_to_or_after_second(String first, String second) {
            String[] first_date = first.split("/");
            String[] second_date = second.split("/");

            int first_day = Integer.parseInt(first_date[0]);
            int first_month = Integer.parseInt(first_date[1]);
            int first_year = Integer.parseInt(first_date[2]);

            int second_day = Integer.parseInt(second_date[0]);
            int second_month = Integer.parseInt(second_date[1]);
            int second_year = Integer.parseInt(second_date[2]);

            if (first_year > second_year) {
                return true;
            } else if (first_year == second_year) {
                if (first_month > second_month) {
                    return true;
                } else if (first_month == second_month) {
                    return first_day >= second_day; // Check for equality or after
                }
            }
            return false;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                if (label.equals("Update")) {
                    // as confirmation before updating
                    int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this record?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (response != JOptionPane.YES_OPTION) {
                        isPushed = false;
                        return new String(label);
                    }

                    int row = taxTable.getSelectedRow();
                    String Customer_ID = taxTable.getValueAt(row, 0).toString();
                    String Billing_Month = taxTable.getValueAt(row, 1).toString();
                    String Billing_Year = taxTable.getValueAt(row, 2).toString();
                    String Current_Reading_Reg = taxTable.getValueAt(row, 3).toString();
                    String Current_Reading_Peak = taxTable.getValueAt(row, 4).toString();
                    String Entry_Date = taxTable.getValueAt(row, 5).toString();
                    String Electricity_Cost = taxTable.getValueAt(row, 6).toString();
                    String Sales_Tax = taxTable.getValueAt(row, 7).toString();
                    String Fixed_Charges = taxTable.getValueAt(row, 8).toString();
                    String Total_Billing_Amount = taxTable.getValueAt(row, 9).toString();
                    String Due_Date = taxTable.getValueAt(row, 10).toString();
                    String Bill_Status = taxTable.getValueAt(row, 11).toString();
                    String Payment_Date = taxTable.getValueAt(row, 12).toString();

                    client cl = new client();
                    String meter_type = (String) cl.sendRequest("customer.getMeterType," + Customer_ID);

                    // Customer customer = new Customer();
                    // String meter_type = customer.getMeterType(Customer_ID);

                    if (!Current_Reading_Reg.matches("[0-9]+") || Integer.parseInt(Current_Reading_Reg) < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid Current Reading (Reg)", "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    } else if (!Electricity_Cost.matches("[0-9]+") || Integer.parseInt(Electricity_Cost) < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid Electricity Cost", "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    } else if (!Sales_Tax.matches("[0-9]+") || Integer.parseInt(Sales_Tax) < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid Sales Tax", "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    } else if (!Fixed_Charges.matches("[0-9]+") || Integer.parseInt(Fixed_Charges) < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid Fixed Charges", "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    } else if (!Total_Billing_Amount.matches("[0-9]+") || Integer.parseInt(Total_Billing_Amount) < 0) {
                        JOptionPane.showMessageDialog(null, "Invalid Total Billing Amount", "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    } else if (!Bill_Status.equals("Paid") && !Bill_Status.equals("Unpaid")) {
                        JOptionPane.showMessageDialog(null, "Invalid Bill Status", "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                    if (is_valid_date_format(Entry_Date) == false) {
                        JOptionPane.showMessageDialog(null, "Invalid Entry Date", "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    } else if (is_valid_date_format(Due_Date) == false) {
                        JOptionPane.showMessageDialog(null, "Invalid Due Date", "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                    if (meter_type.equals("Three Phase")) {
                        if (!Current_Reading_Peak.matches("[0-9]+") || Integer.parseInt(Current_Reading_Peak) < 0) {
                            JOptionPane.showMessageDialog(null, "Invalid Current Reading (Peak)", "Invalid Input",
                                    JOptionPane.ERROR_MESSAGE);
                            isPushed = false;
                            return new String(label);
                        }
                    } else {
                        Current_Reading_Peak = "";
                    }

                    if (Payment_Date.equals("") == false) {
                        if (is_valid_date_format(Payment_Date) == false) {
                            JOptionPane.showMessageDialog(null, "Invalid Payment Date", "Invalid Input",
                                    JOptionPane.ERROR_MESSAGE);
                            isPushed = false;
                            return new String(label);
                        }

                        if (first_is_equal_to_or_after_second(Payment_Date, Entry_Date) == false) {
                            JOptionPane.showMessageDialog(null, "Payment Date should be after Entry Date",
                                    "Invalid Input",
                                    JOptionPane.ERROR_MESSAGE);
                            isPushed = false;
                            return new String(label);
                        }
                    }

                    if (first_is_after_second(Due_Date, Entry_Date) == false) {
                        JOptionPane.showMessageDialog(null, "Due Date should be after Entry Date", "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                    // Billing_Data billing_data = new Billing_Data(Customer_ID, Billing_Month,
                    // Billing_Year,
                    // Current_Reading_Reg, Current_Reading_Peak, Entry_Date, Electricity_Cost,
                    // Sales_Tax,
                    // Fixed_Charges, Total_Billing_Amount, Due_Date, Bill_Status, Payment_Date);

                    // Billing billing = new Billing();

                    // boolean success = billing.update_edit_billing_data(billing_data);
                    // billing.save_data();
                    // billing.load_data();

                    boolean success = (boolean) cl.sendRequest("billing.update_edit_billing_data," + Customer_ID + ","
                            + Billing_Month + "," + Billing_Year + "," + Current_Reading_Reg + ","
                            + Current_Reading_Peak
                            + "," + Entry_Date + "," + Electricity_Cost + "," + Sales_Tax + "," + Fixed_Charges + ","
                            + Total_Billing_Amount + "," + Due_Date + "," + Bill_Status + "," + Payment_Date);

                    if (success == true) {
                        JOptionPane.showMessageDialog(null, "Billing data updated successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update billing data", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    }

                } else if (label.equals("Delete")) {
                    int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (response != JOptionPane.YES_OPTION) {
                        isPushed = false;
                        return new String(label);
                    }
                    int row = taxTable.getSelectedRow();
                    String Customer_ID = taxTable.getValueAt(row, 0).toString();
                    String Billing_Month = taxTable.getValueAt(row, 1).toString();
                    String Billing_Year = taxTable.getValueAt(row, 2).toString();
                    String Entry_Date = taxTable.getValueAt(row, 5).toString();

                    client cl = new client();
                    boolean success = (boolean) cl.sendRequest(
                            "billing.delete_bill," + Customer_ID + "," + Billing_Month + "," + Billing_Year + ","
                                    + Entry_Date);

                    // Billing billing = new Billing();
                    // boolean success = billing.delete_bill(Customer_ID, Billing_Month,
                    // Billing_Year, Entry_Date);
                    // billing.save_data();
                    // billing.load_data();

                    if (success) {
                        DefaultTableModel model = (DefaultTableModel) taxTable.getModel();
                        model.removeRow(row);

                        JOptionPane.showMessageDialog(null, "Billing data deleted successfully", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        isPushed = false;
                        return new String(label);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete billing data", "Error",
                                JOptionPane.ERROR_MESSAGE);
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

        public void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}