package View;

import javax.swing.*;

import Client.client;

import java.awt.*;

public class UpdateTaxInfo extends JPanel {
    public ButtonGroup customerTypeGroup;
    public ButtonGroup meterTypeGroup;
    public JButton updateButton;
    public JLabel titleLabel;
    public JLabel customerTypeLabel;
    public JLabel meterTypeLabel;
    public JLabel pricePeakUnitLabel;
    public JLabel priceRegUnitLabel;
    public JLabel taxPercentLabel;
    public JLabel fixedChargesLabel;
    public JRadioButton commercialRadioButton;
    public JRadioButton domesticRadioButton;
    public JRadioButton singlePhaseRadioButton;
    public JRadioButton threePhaseRadioButton;
    public JTextField pricePeakUnitField;
    public JTextField priceRegUnitField;
    public JTextField taxPercentField;
    public JTextField fixedChargesField;

    public UpdateTaxInfo() {
        init();
    }

    private void init() {
        setBackground(Color.WHITE);
        meterTypeGroup = new javax.swing.ButtonGroup();
        customerTypeGroup = new javax.swing.ButtonGroup();
        titleLabel = new javax.swing.JLabel();
        commercialRadioButton = new javax.swing.JRadioButton();
        domesticRadioButton = new javax.swing.JRadioButton();
        customerTypeLabel = new javax.swing.JLabel();
        singlePhaseRadioButton = new javax.swing.JRadioButton();
        threePhaseRadioButton = new javax.swing.JRadioButton();
        pricePeakUnitLabel = new javax.swing.JLabel();
        meterTypeLabel = new javax.swing.JLabel();
        priceRegUnitLabel = new javax.swing.JLabel();
        pricePeakUnitField = new javax.swing.JTextField();
        priceRegUnitField = new javax.swing.JTextField();
        taxPercentLabel = new javax.swing.JLabel();
        fixedChargesLabel = new javax.swing.JLabel();
        taxPercentField = new javax.swing.JTextField();
        fixedChargesField = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();

        commercialRadioButton.setBackground(Color.WHITE);
        domesticRadioButton.setBackground(Color.WHITE);
        singlePhaseRadioButton.setBackground(Color.WHITE);
        threePhaseRadioButton.setBackground(Color.WHITE);

        setMaximumSize(new java.awt.Dimension(730, 700));
        setMinimumSize(new java.awt.Dimension(730, 700));
        setLayout(null);

        titleLabel.setFont(new java.awt.Font("Arial", 1, 24));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Update Tax Info");
        titleLabel.setBounds(260, 40, 200, 30);

        commercialRadioButton.setFont(new java.awt.Font("Arial", 0, 12));
        commercialRadioButton.setText("Commercial");
        commercialRadioButton.setBounds(390, 130, 100, 20);
        commercialRadioButton.setSelected(true); // default selected

        domesticRadioButton.setFont(new java.awt.Font("Arial", 0, 12));
        domesticRadioButton.setText("Domestic");
        domesticRadioButton.setBounds(390, 160, 100, 20);

        customerTypeLabel.setFont(new java.awt.Font("Arial", 1, 18));
        customerTypeLabel.setText("Customer Type:");
        customerTypeLabel.setBounds(220, 130, 150, 20);

        singlePhaseRadioButton.setFont(new java.awt.Font("Arial", 0, 12));
        singlePhaseRadioButton.setText("Single Phase");
        singlePhaseRadioButton.setBounds(390, 200, 100, 20);
        singlePhaseRadioButton.setSelected(true); // default selected
        // if single phase radio is selected then disable peak unit price field
        singlePhaseRadioButton.addActionListener(e -> {
            pricePeakUnitField.setEnabled(false);
        });

        threePhaseRadioButton.setFont(new java.awt.Font("Arial", 0, 12));
        threePhaseRadioButton.setText("3 Phase");
        threePhaseRadioButton.setBounds(390, 230, 100, 20);
        // if 3 phase radio is selected then enable peak unit price field
        threePhaseRadioButton.addActionListener(e -> {
            pricePeakUnitField.setEnabled(true);
        });

        pricePeakUnitLabel.setFont(new java.awt.Font("Arial", 1, 18));
        pricePeakUnitLabel.setText("Price (Peak Unit):");
        pricePeakUnitLabel.setBounds(170, 320, 150, 20);

        meterTypeLabel.setFont(new java.awt.Font("Arial", 1, 18));
        meterTypeLabel.setText("Meter Type:");
        meterTypeLabel.setBounds(220, 200, 150, 20);

        priceRegUnitLabel.setFont(new java.awt.Font("Arial", 1, 18));
        priceRegUnitLabel.setText("Price (Reg Unit):");
        priceRegUnitLabel.setBounds(170, 270, 150, 20);

        pricePeakUnitField.setBounds(340, 320, 200, 30);
        pricePeakUnitField.setHorizontalAlignment(JTextField.CENTER);
        pricePeakUnitField.setEnabled(false); // by defualt disabled

        priceRegUnitField.setBounds(340, 270, 200, 30);
        priceRegUnitField.setHorizontalAlignment(JTextField.CENTER);

        taxPercentLabel.setFont(new java.awt.Font("Arial", 1, 18));
        taxPercentLabel.setText("Tax (Percent):");
        taxPercentLabel.setBounds(170, 370, 150, 20);

        fixedChargesLabel.setFont(new java.awt.Font("Arial", 1, 18));
        fixedChargesLabel.setText("Fixed Charges:");
        fixedChargesLabel.setBounds(170, 420, 150, 20);

        taxPercentField.setBounds(340, 370, 200, 30);
        taxPercentField.setHorizontalAlignment(JTextField.CENTER);

        fixedChargesField.setBounds(340, 420, 200, 30);
        fixedChargesField.setHorizontalAlignment(JTextField.CENTER);

        add(titleLabel);
        add(commercialRadioButton);
        add(domesticRadioButton);
        add(customerTypeLabel);
        add(singlePhaseRadioButton);
        add(threePhaseRadioButton);
        add(pricePeakUnitLabel);
        add(meterTypeLabel);
        customerTypeGroup.add(commercialRadioButton);
        customerTypeGroup.add(domesticRadioButton);
        meterTypeGroup.add(singlePhaseRadioButton);
        meterTypeGroup.add(threePhaseRadioButton);
        add(priceRegUnitLabel);
        add(pricePeakUnitField);
        add(priceRegUnitField);
        add(taxPercentLabel);
        add(fixedChargesLabel);
        add(taxPercentField);
        add(fixedChargesField);
        add(fixedChargesField);

        updateButton.setFont(new java.awt.Font("Arial", 1, 18));
        updateButton.setText("Update");
        updateButton.setBounds(290, 530, 150, 40);
        updateButton.setFocusable(false);
        // add button click event
        updateButton.addActionListener(e -> {
            String customerType = commercialRadioButton.isSelected() ? "Commercial" : "Domestic";
            String meterType = singlePhaseRadioButton.isSelected() ? "Single Phase" : "3 Phase";
            String pricePeakUnit = pricePeakUnitField.getText();
            String priceRegUnit = priceRegUnitField.getText();
            String taxPercent = taxPercentField.getText();
            String fixedCharges = fixedChargesField.getText();
            if (pricePeakUnit.isEmpty() || priceRegUnit.isEmpty() || taxPercent.isEmpty() || fixedCharges.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                if (pricePeakUnit.matches("[0-9]+") && priceRegUnit.matches("[0-9]+") && taxPercent.matches("[0-9]+")
                        && fixedCharges.matches("[0-9]+")) {
                    // DO NOTHING
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers (greater then 0)", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (Double.parseDouble(pricePeakUnit) < 1 || Double.parseDouble(priceRegUnit) < 1
                        || Double.parseDouble(taxPercent) < 1 || Double.parseDouble(fixedCharges) < 1) {
                    JOptionPane.showMessageDialog(null, "Numbers cannot be less then 1", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (meterType.equals("3 Phase")) {
                pricePeakUnit = pricePeakUnitField.getText();
            } else {
                pricePeakUnit = "";
            }

            client cl = new client();

            // TariffTax tax = new TariffTax();
            int option = 0;
            if (customerType.equals("Commercial") && meterType.equals("Single Phase")) {
                option = 2;
            } else if (customerType.equals("Domestic") && meterType.equals("Single Phase")) {
                option = 1;
            } else if (customerType.equals("Commercial") && meterType.equals("3 Phase")) {
                option = 4;
            } else if (customerType.equals("Domestic") && meterType.equals("3 Phase")) {
                option = 3;
            }

            cl.sendRequest("tax.update_tarrif," + priceRegUnit + "," + pricePeakUnit + "," + taxPercent + ","
                    + fixedCharges + "," + option);
            // tax.update_tarrif(priceRegUnit, pricePeakUnit, taxPercent, fixedCharges,
            // option);

            // clear all the fields
            pricePeakUnitField.setText("");
            priceRegUnitField.setText("");
            taxPercentField.setText("");
            fixedChargesField.setText("");

            JOptionPane.showMessageDialog(null, "Tax info updated successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        });
        add(updateButton);
    }
}
