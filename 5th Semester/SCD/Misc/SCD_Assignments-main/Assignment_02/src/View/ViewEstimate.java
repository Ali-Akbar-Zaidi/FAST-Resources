package View;

import javax.swing.*;

import Controller.billing.TariffTax;
import Controller.schemas.Tariff_Data;

import java.awt.*;

public class ViewEstimate extends javax.swing.JPanel {

    public ButtonGroup customerTypeGroup;
    public ButtonGroup meterTypeGroup;
    public JButton viewButton;
    public JLabel titleLabel;
    public JLabel meterTypeLabel;
    public JLabel peakUnitsLabel;
    public JLabel customerTypeLabel;
    public JLabel regularUnitsLabel;
    public JRadioButton threePhaseRadioButton;
    public JRadioButton singlePhaseRadioButton;
    public JRadioButton domesticRadioButton;
    public JRadioButton commercialRadioButton;
    public JTextField peakUnitsTextField;
    public JTextField regularUnitsTextField;

    public ViewEstimate() {
        init();
    }

    private void init() {

        meterTypeGroup = new javax.swing.ButtonGroup();
        customerTypeGroup = new javax.swing.ButtonGroup();
        titleLabel = new javax.swing.JLabel();
        meterTypeLabel = new javax.swing.JLabel();
        threePhaseRadioButton = new javax.swing.JRadioButton();
        singlePhaseRadioButton = new javax.swing.JRadioButton();
        peakUnitsLabel = new javax.swing.JLabel();
        domesticRadioButton = new javax.swing.JRadioButton();
        commercialRadioButton = new javax.swing.JRadioButton();
        customerTypeLabel = new javax.swing.JLabel();
        peakUnitsTextField = new javax.swing.JTextField();
        regularUnitsLabel = new javax.swing.JLabel();
        regularUnitsTextField = new javax.swing.JTextField();
        viewButton = new javax.swing.JButton();
        setSize(730, 700);

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(730, 700));
        setMinimumSize(new java.awt.Dimension(730, 700));
        setLayout(null);

        titleLabel.setFont(new java.awt.Font("Arial", 1, 36));
        titleLabel.setText("View Estimate");
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        titleLabel.setBounds(240, 60, 250, 30);
        add(titleLabel);

        meterTypeLabel.setFont(new java.awt.Font("Arial", 1, 24));
        meterTypeLabel.setText("Meter Type:");
        meterTypeLabel.setBounds(200, 160, 150, 30);
        add(meterTypeLabel);

        meterTypeGroup.add(threePhaseRadioButton);
        threePhaseRadioButton.setFont(new java.awt.Font("Arial", 1, 18));
        threePhaseRadioButton.setText("Three Phase");
        threePhaseRadioButton.setBounds(360, 210, 150, 30);
        threePhaseRadioButton.setSelected(true); // default selection
        threePhaseRadioButton.setBackground(Color.WHITE);
        threePhaseRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threePhaseRadioButton.setSelected(true);
                peakUnitsTextField.setText("");
                peakUnitsTextField.setEnabled(true);
            }
        });
        add(threePhaseRadioButton);

        meterTypeGroup.add(singlePhaseRadioButton);
        singlePhaseRadioButton.setFont(new java.awt.Font("Arial", 1, 18));
        singlePhaseRadioButton.setText("Single Phase");
        singlePhaseRadioButton.setBounds(200, 210, 150, 30);
        singlePhaseRadioButton.setBackground(Color.WHITE);
        singlePhaseRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singlePhaseRadioButton.setSelected(true);
                // enable peak units text field
                peakUnitsTextField.setEnabled(false);
            }
        });
        add(singlePhaseRadioButton);

        peakUnitsLabel.setFont(new java.awt.Font("Arial", 1, 24));
        peakUnitsLabel.setText("Peak Units:");
        peakUnitsLabel.setBounds(200, 370, 150, 30);
        add(peakUnitsLabel);

        customerTypeGroup.add(domesticRadioButton);
        domesticRadioButton.setFont(new java.awt.Font("Arial", 1, 18));
        domesticRadioButton.setText("Domestic");
        domesticRadioButton.setBounds(200, 320, 150, 30);
        domesticRadioButton.setSelected(true); // default selection
        domesticRadioButton.setBackground(Color.WHITE);
        domesticRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                domesticRadioButton.setSelected(true);
            }
        });
        add(domesticRadioButton);

        customerTypeGroup.add(commercialRadioButton);
        commercialRadioButton.setFont(new java.awt.Font("Arial", 1, 18));
        commercialRadioButton.setText("Commercial");
        commercialRadioButton.setBounds(360, 320, 150, 30);
        commercialRadioButton.setBackground(Color.WHITE);
        commercialRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commercialRadioButton.setSelected(true);
            }
        });
        add(commercialRadioButton);

        customerTypeLabel.setFont(new java.awt.Font("Arial", 1, 24));
        customerTypeLabel.setText("Customer Type:");
        customerTypeLabel.setBounds(200, 270, 200, 30);
        add(customerTypeLabel);

        peakUnitsTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        peakUnitsTextField.setBounds(200, 410, 290, 30);
        add(peakUnitsTextField);

        regularUnitsLabel.setFont(new java.awt.Font("Arial", 1, 24));
        regularUnitsLabel.setText("Regular Units:");
        regularUnitsLabel.setBounds(200, 460, 200, 30);
        add(regularUnitsLabel);

        regularUnitsTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        regularUnitsTextField.setBounds(200, 500, 290, 30);
        add(regularUnitsTextField);

        viewButton.setFont(new java.awt.Font("Arial", 1, 24));
        viewButton.setText("View");
        viewButton.setBounds(290, 570, 150, 50);
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String meterType = threePhaseRadioButton.isSelected() ? "Three Phase" : "Single Phase";
                String customerType = domesticRadioButton.isSelected() ? "Domestic" : "Commercial";
                String peakUnits = peakUnitsTextField.getText();
                String regularUnits = regularUnitsTextField.getText();

                if (!meterType.equals("Single Phase") && !meterType.equals("Three Phase")) {
                    JOptionPane.showMessageDialog(null, "Please select a meter type", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (customerType.equals("Domestic") == false && customerType.equals("Commercial") == false) {
                    JOptionPane.showMessageDialog(null, "Please select a customer type", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (regularUnits.matches("[0-9]+") == false || Integer.parseInt(regularUnits) < 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid regular units (nums only >= 0)", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (meterType.equals("Three Phase")) {
                    if (peakUnits.matches("[0-9]+") == false || Integer.parseInt(peakUnits) < 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid peak units (nums only >= 0)", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if (meterType.equals("Single Phase")) {
                    peakUnits = "0";
                }

                if (meterType.equals("Single Phase")) {
                    meterType = "1 Phase";
                } else if (meterType.equals("Three Phase")) {
                    meterType = "3 Phase";
                }

                TariffTax tx = new TariffTax();
                Tariff_Data td = tx.getTax(meterType, customerType);

                int peak_price = 0;

                if (meterType.equals("3 Phase")) {
                    peak_price = Integer.parseInt(td.peak_unit_price);
                }

                int total_electric = (Integer.parseInt(regularUnits) * Integer.parseInt(td.reg_unit_price))
                        + (Integer.parseInt(peakUnits) * peak_price);

                int tax_percentage = Integer.parseInt(td.percent_of_Tax);

                int taxed = total_electric
                        + calculatePercentage(tax_percentage, total_electric);

                int tax = calculatePercentage(tax_percentage, total_electric);

                int final_price = taxed + Integer.parseInt(td.fixed_charges);

                new ViewEstimatePopUp(meterType, customerType, total_electric, td.percent_of_Tax, tax, td.fixed_charges,
                        final_price);

            }
        });
        add(viewButton);
    }

    public static int calculatePercentage(int percentage, int total) {
        return (int) ((percentage / 100.0) * total);
    }
}

class ViewEstimatePopUp extends JFrame {
    public ViewEstimatePopUp(String meterType, String customerType, int electricityCost, String taxPercent, int tax,
            String fixedAmount, int finalPrice) {
        init(meterType, customerType, electricityCost, taxPercent, tax, fixedAmount, finalPrice);
    }

    public void init(String meterType, String customerType, int electricityCost, String taxPercent, int tax,
            String fixedAmount, int finalPrice) {

        // create a small panel with this information centered
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);
        setIconImage(new Logo().getLogo());

        panel.add(new JLabel("Meter: " + meterType), gbc);
        panel.add(new JLabel("Customer: " + customerType), gbc);
        panel.add(new JLabel("Electricity Cost: " + electricityCost), gbc);
        panel.add(new JLabel("Tax (Percent): " + taxPercent + "%"), gbc);
        panel.add(new JLabel("Tax: " + tax), gbc);
        panel.add(new JLabel("Fixed Amount: " + fixedAmount), gbc);
        panel.add(new JLabel("Final Billing Amount: " + finalPrice), gbc);
        panel.setBackground(Color.WHITE);

        add(panel);
        setTitle("Billing Estimate");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}