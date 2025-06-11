package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import Controller.billing.TariffTax;
import Controller.schemas.Tariff_Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTaxFile extends JFrame {

    public JLabel searchLabel;
    public JPanel mainPanel;
    public JScrollPane tableScrollPane;
    public JTable taxTable;
    public JTextField searchTextField;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public EditTaxFile() {
        init();
    }

    private void init() {
        setIconImage(new Logo().getLogo());
        mainPanel = new JPanel();
        searchTextField = new JTextField();
        searchLabel = new JLabel();
        tableScrollPane = new JScrollPane();
        taxTable = new JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tariff Tax File");
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

        TariffTax tarriftax = new TariffTax();
        Object[][] data = new Object[4][6];
        Tariff_Data d_1 = tarriftax.domestic_1_phase;
        Tariff_Data c_1 = tarriftax.commercial_1_phase;
        Tariff_Data d_3 = tarriftax.domestic_3_phase;
        Tariff_Data c_3 = tarriftax.commercial_3_phase;

        data[0][0] = d_1.Meter_Type;
        data[0][1] = d_1.reg_unit_price;
        data[0][2] = d_1.peak_unit_price;
        data[0][3] = d_1.percent_of_Tax;
        data[0][4] = d_1.fixed_charges;
        data[0][5] = "Update";

        data[1][0] = c_1.Meter_Type;
        data[1][1] = c_1.reg_unit_price;
        data[1][2] = c_1.peak_unit_price;
        data[1][3] = c_1.percent_of_Tax;
        data[1][4] = c_1.fixed_charges;
        data[1][5] = "Update";

        data[2][0] = d_3.Meter_Type;
        data[2][1] = d_3.reg_unit_price;
        data[2][2] = d_3.peak_unit_price;
        data[2][3] = d_3.percent_of_Tax;
        data[2][4] = d_3.fixed_charges;
        data[2][5] = "Update";

        data[3][0] = c_3.Meter_Type;
        data[3][1] = c_3.reg_unit_price;
        data[3][2] = c_3.peak_unit_price;
        data[3][3] = c_3.percent_of_Tax;
        data[3][4] = c_3.fixed_charges;
        data[3][5] = "Update";

        DefaultTableModel model = new DefaultTableModel(
                data,
                new String[] { "Meter Type", "Price (Regular Units)", "Price (Peak Units)", "Tax (Percentage)",
                        "Fixed Charges", "Update" }) {
            boolean[] canEdit = new boolean[] { false, true, true, true, true, true };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if (columnIndex == 2 && (rowIndex == 0 || rowIndex == 1)) {
                    return false;
                }
                return canEdit[columnIndex];
            }
        };

        taxTable.setModel(model);
        rowSorter = new TableRowSorter<>(model);
        taxTable.setRowSorter(rowSorter);

        taxTable.setRowHeight(40);
        taxTable.setFillsViewportHeight(true);
        taxTable.getTableHeader().setReorderingAllowed(false);

        // Center text in cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < taxTable.getColumnCount(); i++) {
            taxTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Add button to the "Update" column
        TableColumn updateColumn = taxTable.getColumnModel().getColumn(5);
        updateColumn.setCellRenderer(new ButtonRenderer());
        updateColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

        tableScrollPane.setViewportView(taxTable);
        if (taxTable.getColumnModel().getColumnCount() > 0) {
            taxTable.getColumnModel().getColumn(0).setResizable(false);
            taxTable.getColumnModel().getColumn(1).setResizable(false);
            taxTable.getColumnModel().getColumn(2).setResizable(false);
            taxTable.getColumnModel().getColumn(3).setResizable(false);
            taxTable.getColumnModel().getColumn(4).setResizable(false);
            taxTable.getColumnModel().getColumn(5).setResizable(false);
        }

        tableScrollPane.setBounds(0, 100, 1000, 600);
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
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Update" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            label = (value == null) ? "Update" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                int row = taxTable.getSelectedRow();
                String meterType = (String) taxTable.getValueAt(row, 0);
                String priceRegUnits = (String) taxTable.getValueAt(row, 1);
                String pricePeakUnits = (String) taxTable.getValueAt(row, 2);
                String taxPercentage = (String) taxTable.getValueAt(row, 3);
                String fixedCharges = (String) taxTable.getValueAt(row, 4);

                if (!priceRegUnits.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Price (Regular Units) must be a number", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    isPushed = false;
                    return label;
                } else if (Integer.parseInt(priceRegUnits) < 0) {
                    JOptionPane.showMessageDialog(null, "Price (Regular Units) must be greater than 0", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    isPushed = false;
                    return label;
                }

                if (!taxPercentage.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Tax (Percentage) must be a number", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    isPushed = false;
                    return label;
                } else if (Integer.parseInt(taxPercentage) < 0) {
                    JOptionPane.showMessageDialog(null, "Tax (Percentage) must be greater than 0", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    isPushed = false;
                    return label;
                }

                if (!fixedCharges.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Fixed Charges must be a number", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    isPushed = false;
                    return label;
                } else if (Integer.parseInt(fixedCharges) < 0) {
                    JOptionPane.showMessageDialog(null, "Fixed Charges must be greater than 0", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    isPushed = false;
                    return label;
                }

                if (meterType.equals("1 Phase Domestic")) {
                    Tariff_Data temp = new Tariff_Data();
                    temp.Meter_Type = meterType;
                    temp.fixed_charges = fixedCharges;
                    temp.percent_of_Tax = taxPercentage;
                    temp.peak_unit_price = pricePeakUnits;
                    temp.reg_unit_price = priceRegUnits;

                    TariffTax tarriftax_temp = new TariffTax();
                    tarriftax_temp.domestic_1_phase = temp;
                    tarriftax_temp.save_data();
                    tarriftax_temp.load_data();
                    JOptionPane.showMessageDialog(null, "Data updated successfully", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    isPushed = false;
                    return label;
                } else if (meterType.equals("1 Phase Commercial")) {
                    Tariff_Data temp = new Tariff_Data();
                    temp.Meter_Type = meterType;
                    temp.fixed_charges = fixedCharges;
                    temp.percent_of_Tax = taxPercentage;
                    temp.peak_unit_price = pricePeakUnits;
                    temp.reg_unit_price = priceRegUnits;

                    TariffTax tarriftax_temp = new TariffTax();
                    tarriftax_temp.commercial_1_phase = temp;
                    tarriftax_temp.save_data();
                    tarriftax_temp.load_data();
                    JOptionPane.showMessageDialog(null, "Data updated successfully", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    isPushed = false;
                    return label;
                } else if (meterType.equals("3 Phase Domestic")) {
                    if (!pricePeakUnits.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(null, "Price (Peak Units) must be a number", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return label;
                    } else if (Integer.parseInt(pricePeakUnits) < 0) {
                        JOptionPane.showMessageDialog(null, "Price (Peak Units) must be greater than 0", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return label;
                    }

                    Tariff_Data temp = new Tariff_Data();
                    temp.Meter_Type = meterType;
                    temp.fixed_charges = fixedCharges;
                    temp.percent_of_Tax = taxPercentage;
                    temp.peak_unit_price = pricePeakUnits;
                    temp.reg_unit_price = priceRegUnits;

                    TariffTax tarriftax_temp = new TariffTax();
                    tarriftax_temp.domestic_3_phase = temp;
                    tarriftax_temp.save_data();
                    tarriftax_temp.load_data();
                    JOptionPane.showMessageDialog(null, "Data updated successfully", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    isPushed = false;
                    return label;
                } else if (meterType.equals("3 Phase Commercial")) {
                    if (!pricePeakUnits.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(null, "Price (Peak Units) must be a number", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return label;
                    } else if (Integer.parseInt(pricePeakUnits) < 0) {
                        JOptionPane.showMessageDialog(null, "Price (Peak Units) must be greater than 0", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        isPushed = false;
                        return label;
                    }

                    Tariff_Data temp = new Tariff_Data();
                    temp.Meter_Type = meterType;
                    temp.fixed_charges = fixedCharges;
                    temp.percent_of_Tax = taxPercentage;
                    temp.peak_unit_price = pricePeakUnits;
                    temp.reg_unit_price = priceRegUnits;

                    TariffTax tarriftax_temp = new TariffTax();
                    tarriftax_temp.commercial_3_phase = temp;
                    tarriftax_temp.save_data();
                    tarriftax_temp.load_data();
                    JOptionPane.showMessageDialog(null, "Data updated successfully", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    isPushed = false;
                    return label;
                }

            }
            isPushed = false;
            return label;
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
