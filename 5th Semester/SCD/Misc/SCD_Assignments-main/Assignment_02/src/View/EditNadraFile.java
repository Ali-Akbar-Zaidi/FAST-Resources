package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import Controller.authentication.NADRA;
import Controller.schemas.NADRA_Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditNadraFile extends JFrame {

    public JLabel searchLabel;
    public JPanel mainPanel;
    public JScrollPane tableScrollPane;
    public JTable taxTable;
    public JTextField searchTextField;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public EditNadraFile() {
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
        setTitle("Nadra Data File");
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

        NADRA nadra = new NADRA();
        int size = nadra.nadra_data.size();
        Object[][] data = new Object[size][4];

        for (int i = 0; i < size; i++) {
            NADRA_Data row = nadra.nadra_data.get(i);
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = row.CNIC;
            data[i][2] = row.issue_date;
            data[i][3] = row.expiry_date;
        }

        DefaultTableModel model = new DefaultTableModel(
                data,
                new String[] { "Index", "CNIC", "Issue Date", "Expiry Date", "Update" }) {
            boolean[] canEdit = new boolean[] { false, true, true, true, true };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
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
        TableColumn updateColumn = taxTable.getColumnModel().getColumn(4);
        updateColumn.setCellRenderer(new ButtonRenderer());
        updateColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

        tableScrollPane.setViewportView(taxTable);
        if (taxTable.getColumnModel().getColumnCount() > 0) {
            taxTable.getColumnModel().getColumn(0).setResizable(false);
            taxTable.getColumnModel().getColumn(1).setResizable(false);
            taxTable.getColumnModel().getColumn(2).setResizable(false);
            taxTable.getColumnModel().getColumn(3).setResizable(false);
        }

        tableScrollPane.setBounds(0, 100, 1000, 560);
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
            JOptionPane.showMessageDialog(null, "No data found in the NADRA file", "No Data",
                    JOptionPane.INFORMATION_MESSAGE);
        }
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

        public boolean is_valid_date_format(String d) {
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            try {
                df.parse(d);
                return true;
            } catch (Exception exception) {
                return false;
            }
        }

        public boolean date_comes_before_today(String date) {
            java.time.format.DateTimeFormatter f = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                java.time.LocalDate i = java.time.LocalDate.parse(date, f);
                java.time.LocalDate td = java.time.LocalDate.now();
                return !i.isBefore(td);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid date format", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                int row = taxTable.getSelectedRow();
                String index = (String) taxTable.getValueAt(row, 0);
                String CNIC = (String) taxTable.getValueAt(row, 1);
                String issueDate = (String) taxTable.getValueAt(row, 2);
                String expiryDate = (String) taxTable.getValueAt(row, 3);

                if (CNIC.length() != 13 || !CNIC.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Invalid CNIC (13 Digits required)", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    isPushed = false;
                    return label;
                }

                if (issueDate.length() != 10 || is_valid_date_format(issueDate) == false
                        || date_comes_before_today(issueDate)) {
                    JOptionPane.showMessageDialog(null, "Invalid Issue Date (YYYY-MM-DD)", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    isPushed = false;
                    return label;
                }

                while (expiryDate.length() != 10 || is_valid_date_format(expiryDate) == false
                        || !date_comes_before_today(expiryDate)) {
                    JOptionPane.showMessageDialog(null, "Invalid Expiry Date (YYYY-MM-DD)", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    isPushed = false;
                    return label;
                }

                int index_int = Integer.parseInt(index) - 1;
                NADRA nadra = new NADRA();
                if (nadra.cnic_exits_except_index(CNIC, index_int)) {
                    JOptionPane.showMessageDialog(null, "CNIC already exists", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    isPushed = false;
                    return label;
                }
                NADRA_Data NADRA_DATA_TEMP = nadra.nadra_data.get(index_int);
                NADRA_DATA_TEMP.CNIC = CNIC;
                NADRA_DATA_TEMP.issue_date = issueDate;
                NADRA_DATA_TEMP.expiry_date = expiryDate;
                nadra.nadra_data.set(index_int, NADRA_DATA_TEMP);

                nadra.save_data();
                nadra.load_data();

                JOptionPane.showMessageDialog(null, "Data updated successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
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
