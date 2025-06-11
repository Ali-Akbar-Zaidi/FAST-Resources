package View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Client.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import Controller.schemas.Customer_Data;

public class UpdateCustomerData extends javax.swing.JPanel {
    public JLabel titleLabel;
    public JScrollPane tableScrollPane;
    public JTable customerTable;

    public UpdateCustomerData() {
        init();
    }

    @SuppressWarnings("unchecked")
    private void init() {
        titleLabel = new javax.swing.JLabel();
        tableScrollPane = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(730, 700));
        setMinimumSize(new java.awt.Dimension(730, 700));
        setLayout(null);

        titleLabel.setFont(new java.awt.Font("Arial", 1, 24));
        titleLabel.setText("Select Customer");
        titleLabel.setBounds(270, 40, 190, 30);
        add(titleLabel);

        client cl = new client();

        // Customer customer = new Customer();
        ArrayList<Customer_Data> customer_data = (ArrayList<Customer_Data>) cl.sendRequest("customer.customer_data");
        int size = customer_data.size();
        Object[][] data = new Object[size][10];

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
        }

        customerTable.setModel(new DefaultTableModel(
                data,
                new String[] {
                        "ID", "CNIC", "Name", "Address", "Phone", "Customer Type", "Meter Type", "Units Consumed (Reg)",
                        "Units Consumed (Peak)", "Update"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        customerTable.setBounds(0, 90, 750, 610);
        customerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        customerTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        customerTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        customerTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        customerTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        customerTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        customerTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        customerTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        customerTable.getColumnModel().getColumn(7).setPreferredWidth(100);
        customerTable.getColumnModel().getColumn(8).setPreferredWidth(100);
        customerTable.getColumnModel().getColumn(9).setPreferredWidth(100);

        // Center text in cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < customerTable.getColumnCount(); i++) {
            customerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        customerTable.getTableHeader().setReorderingAllowed(false);
        customerTable.setFillsViewportHeight(true);
        tableScrollPane.setViewportView(customerTable);
        if (customerTable.getColumnModel().getColumnCount() > 0) {
            customerTable.getColumnModel().getColumn(0).setResizable(false);
            customerTable.getColumnModel().getColumn(1).setResizable(false);
            customerTable.getColumnModel().getColumn(2).setResizable(false);
            customerTable.getColumnModel().getColumn(3).setResizable(false);
            customerTable.getColumnModel().getColumn(4).setResizable(false);
            customerTable.getColumnModel().getColumn(5).setResizable(false);
            customerTable.getColumnModel().getColumn(6).setResizable(false);
            customerTable.getColumnModel().getColumn(7).setResizable(false);
            customerTable.getColumnModel().getColumn(8).setResizable(false);
            customerTable.getColumnModel().getColumn(9).setResizable(false);
        }

        customerTable.getColumn("Update").setCellRenderer(new ButtonRenderer());
        customerTable.getColumn("Update").setCellEditor(new ButtonEditor(new JCheckBox()));
        customerTable.setBackground(Color.white);

        tableScrollPane.setBounds(0, 90, 720, 590);
        tableScrollPane.setBackground(Color.white);
        add(tableScrollPane);

        setVisible(true);
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private String label;
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            this.row = row;
            JButton button = new JButton();
            button.setText(label);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String val = table.getValueAt(row, 0).toString();
                    UpdateCustomerDataPopup popup = new UpdateCustomerDataPopup(val, row);
                    popup.addWindowListener(new WindowAdapter() {
                        @SuppressWarnings("unchecked")
                        @Override
                        public void windowClosed(WindowEvent e) {
                            // Update the table data after the popup closes

                            // Customer customer = new Customer();
                            client cl = new client();
                            ArrayList<Customer_Data> customer_data_ = (ArrayList<Customer_Data>) cl
                                    .sendRequest("customer.customer_data");
                            Customer_Data customer_data = customer_data_.get(row);
                            table.setValueAt(customer_data.Name, row, 2);
                            table.setValueAt(customer_data.Address, row, 3);
                            table.setValueAt(customer_data.Phone_No, row, 4);
                            table.setValueAt(customer_data.cust_type, row, 5);
                            table.setValueAt(customer_data.meter_type, row, 6);
                        }
                    });

                    fireEditingStopped();
                }
            });
            return button;
        }

        public Object getCellEditorValue() {
            return label;
        }

        public boolean stopCellEditing() {
            fireEditingStopped();
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}

class UpdateCustomerDataPopup extends JFrame {
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
    public JPanel panel;

    public UpdateCustomerDataPopup(String id, int row) {
        init(id, row);
    }

    private void init(String id, int row) {
        setTitle("Update Customer Data");
        setIconImage(new Logo().getLogo());
        setSize(730, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(730, 700);

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
        titleLabel.setText("Update Meter");
        titleLabel.setBounds(220, 70, 285, 38);

        cnicLabel.setFont(new java.awt.Font("Arial", 1, 18));
        cnicLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cnicLabel.setText("CNIC:");
        cnicLabel.setBounds(150, 130, 100, 30);

        cnicTextField.setFont(new java.awt.Font("Arial", 1, 18));
        cnicTextField.setBounds(270, 130, 247, 30);
        cnicTextField.setText("Not Selectable");
        cnicTextField.setEnabled(false); // disabled as not required

        nameLabel.setFont(new java.awt.Font("Arial", 1, 18));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameLabel.setText("Name:");
        nameLabel.setBounds(150, 180, 100, 30);

        nameTextField.setFont(new java.awt.Font("Arial", 1, 18));
        nameTextField.setBounds(270, 180, 247, 30);

        addressLabel.setFont(new java.awt.Font("Arial", 1, 18));
        addressLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addressLabel.setText("Address:");
        addressLabel.setBounds(150, 230, 100, 30);

        addressTextField.setFont(new java.awt.Font("Arial", 1, 18));
        addressTextField.setBounds(270, 230, 247, 30);

        phoneLabel.setFont(new java.awt.Font("Arial", 1, 18));
        phoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        phoneLabel.setText("Phone:");
        phoneLabel.setBounds(150, 280, 100, 30);

        phoneTextField.setFont(new java.awt.Font("Arial", 1, 18));
        phoneTextField.setBounds(270, 280, 247, 30);

        customerTypeLabel.setFont(new java.awt.Font("Arial", 1, 18));
        customerTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        customerTypeLabel.setText("Customer Type:");
        customerTypeLabel.setBounds(220, 330, 285, 30);

        customerTypeGroup.add(commercialRadioButton);
        commercialRadioButton.setFont(new java.awt.Font("Arial", 1, 18));
        commercialRadioButton.setText("Commercial");
        commercialRadioButton.setBounds(370, 370, 150, 30);

        customerTypeGroup.add(domesticRadioButton);
        domesticRadioButton.setFont(new java.awt.Font("Arial", 1, 18));
        domesticRadioButton.setText("Domestic");
        domesticRadioButton.setSelected(true); // default selection
        domesticRadioButton.setBounds(220, 370, 150, 30);

        meterTypeLabel.setFont(new java.awt.Font("Arial", 1, 18));
        meterTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        meterTypeLabel.setText("Meter Type:");
        meterTypeLabel.setBounds(220, 420, 285, 30);

        meterTypeGroup.add(threePhaseRadioButton);
        threePhaseRadioButton.setFont(new java.awt.Font("Arial", 1, 18));
        threePhaseRadioButton.setText("Three Phase");
        threePhaseRadioButton.setBounds(370, 460, 150, 30);

        meterTypeGroup.add(singlePhaseRadioButton);
        singlePhaseRadioButton.setFont(new java.awt.Font("Arial", 1, 18));
        singlePhaseRadioButton.setText("Single Phase");
        singlePhaseRadioButton.setSelected(true); // default selection
        singlePhaseRadioButton.setBounds(220, 460, 150, 30);

        confirmToggleButton.setFont(new java.awt.Font("Arial", 1, 18));
        confirmToggleButton.setText("Confirm");
        confirmToggleButton.setFocusable(false);
        confirmToggleButton.setBounds(279, 550, 172, 48);

        confirmToggleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @SuppressWarnings("unchecked")
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String Name = nameTextField.getText();
                String Address = addressTextField.getText();
                String Phone = phoneTextField.getText();
                String customerType = commercialRadioButton.isSelected() ? "Commercial" : "Domestic";
                String meterType = threePhaseRadioButton.isSelected() ? "Three Phase" : "Single Phase";

                if (Name.length() < 7) {
                    JOptionPane.showMessageDialog(null, "Name must be atleast 7 characters long", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (Address.length() < 7 || Address.contains(",")) {
                    JOptionPane.showMessageDialog(null,
                            "Address must be atleast 7 characters long and should not contain ','", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (Phone.length() != 11 || !Phone.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null,
                            "Phone number must be 11 digits long and should only contain digits",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                client cl = new client();

                // Customer customer = new Customer();
                ArrayList<Customer_Data> customer_data_ = (ArrayList<Customer_Data>) cl
                        .sendRequest("customer.customer_data");
                Customer_Data customer_data = customer_data_.get(row);
                System.out.println("Row: " + row);
                customer_data.Name = Name;
                customer_data.Address = Address;
                customer_data.Phone_No = Phone;
                customer_data.cust_type = customerType;
                customer_data.meter_type = meterType;
                if (meterType.equals("Three Phase")) {
                    if (customer_data.peak_units_comsumed.equals("") == true) {
                        customer_data.peak_units_comsumed = "0";
                    }
                } else {
                    if (customer_data.peak_units_comsumed.equals("") == false) {
                        customer_data.peak_units_comsumed = "";
                    }
                }

                if (customer_data.peak_units_comsumed.equals("")) {
                    customer_data.peak_units_comsumed = "NULL";
                }

                String line = customer_data.getString();

                cl.sendRequest("customer.customer_data.set," + row + "," + line);

                // customer.customer_data.set(row, customer_data);
                // customer.save_data();
                // customer.load_data();

                JOptionPane.showMessageDialog(null, "Customer data updated successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                // destroy this window
                dispose();
            }
        });

        panel.add(titleLabel);
        panel.add(cnicLabel);
        panel.add(cnicTextField);
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(addressLabel);
        panel.add(addressTextField);
        panel.add(phoneLabel);
        panel.add(phoneTextField);
        panel.add(customerTypeLabel);
        panel.add(domesticRadioButton);
        panel.add(commercialRadioButton);
        panel.add(meterTypeLabel);
        panel.add(singlePhaseRadioButton);
        panel.add(threePhaseRadioButton);
        panel.add(confirmToggleButton);

        add(panel);
        setVisible(true);
    }
}
