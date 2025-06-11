package View;

import javax.swing.*;

public class EditFiles extends JPanel {
    private JButton editTaxFileButton;
    private JButton editBillingFileButton;
    private JButton editCustomerFileButton;
    private JButton editNadraFileButton;
    private JLabel titleLabel;

    public EditFiles() {
        init();
    }

    private void init() {
        titleLabel = new JLabel();
        editTaxFileButton = new JButton();
        editBillingFileButton = new JButton();
        editCustomerFileButton = new JButton();
        editNadraFileButton = new JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(730, 700));
        setMinimumSize(new java.awt.Dimension(730, 700));
        setLayout(null);

        titleLabel.setFont(new java.awt.Font("Arial", 1, 36));
        titleLabel.setText("Edit Files");
        titleLabel.setBounds(270, 90, 200, 50);
        add(titleLabel);

        editTaxFileButton.setFont(new java.awt.Font("Arial", 1, 18));
        editTaxFileButton.setText("Edit Tax File");
        editTaxFileButton.setActionCommand("Edit NADRA File");
        // on button click create a new edit tax file
        editTaxFileButton.addActionListener(e -> {
            new EditTaxFile();
        });

        editTaxFileButton.setBounds(250, 460, 210, 50);
        editTaxFileButton.setFocusable(false);
        add(editTaxFileButton);

        editBillingFileButton.setFont(new java.awt.Font("Arial", 1, 18));
        editBillingFileButton.setText("Edit Billing File");
        editBillingFileButton.setFocusable(false);
        editBillingFileButton.setBounds(250, 190, 210, 50);
        editBillingFileButton.addActionListener(e -> {
            new EditBillingFile();
        });
        add(editBillingFileButton);

        editCustomerFileButton.setFont(new java.awt.Font("Arial", 1, 18));
        editCustomerFileButton.setText("Edit Customer File");

        editCustomerFileButton.setFocusable(false);
        editCustomerFileButton.setBounds(250, 280, 210, 50);
        editCustomerFileButton.addActionListener(e -> {
            new EditCustomerFile();
        });
        add(editCustomerFileButton);

        editNadraFileButton.setFont(new java.awt.Font("Arial", 1, 18));
        editNadraFileButton.setText("Edit NADRA File");
        editNadraFileButton.setFocusable(false);
        editNadraFileButton.setBounds(250, 370, 210, 50);
        editNadraFileButton.addActionListener(e -> {
            new EditNadraFile();
        });
        add(editNadraFileButton);
    }
}