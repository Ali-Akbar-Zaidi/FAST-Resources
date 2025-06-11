package View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.*;

import Client.client;

public class UpdateCNIC extends javax.swing.JPanel {
    public JButton updateButton;
    public JLabel titleLabel;
    public JLabel cnicLabel;
    public JLabel issueDateLabel;
    public JLabel expiryDateLabel;
    public JTextField cnicTextField;
    public JTextField issueDateTextField;
    public JTextField expiryDateTextField;
    final static String FORMAT = "dd/MM/yyyy";

    public UpdateCNIC() {
        init();
    }

    private void init() {

        titleLabel = new javax.swing.JLabel();
        cnicLabel = new javax.swing.JLabel();
        cnicTextField = new javax.swing.JTextField();
        issueDateLabel = new javax.swing.JLabel();
        issueDateTextField = new javax.swing.JTextField();
        expiryDateLabel = new javax.swing.JLabel();
        expiryDateTextField = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(730, 700));
        setMinimumSize(new java.awt.Dimension(730, 700));
        setLayout(null);

        titleLabel.setFont(new java.awt.Font("Arial", 1, 36));
        titleLabel.setText("Update CNIC");
        titleLabel.setBounds(260, 90, 290, 40);
        add(titleLabel);

        cnicLabel.setFont(new java.awt.Font("Arial", 1, 18));
        cnicLabel.setText("CNIC:");
        cnicLabel.setBounds(200, 240, 60, 30);
        add(cnicLabel);

        cnicTextField.setBounds(280, 240, 250, 30);
        add(cnicTextField);

        issueDateLabel.setFont(new java.awt.Font("Arial", 1, 18));
        issueDateLabel.setText("Issue Date:");
        issueDateLabel.setBounds(200, 300, 100, 30);
        add(issueDateLabel);

        issueDateTextField.setBounds(340, 300, 190, 30);
        add(issueDateTextField);

        expiryDateLabel.setFont(new java.awt.Font("Arial", 1, 18));
        expiryDateLabel.setText("Expiry Date:");
        expiryDateLabel.setBounds(200, 360, 120, 30);
        add(expiryDateLabel);

        expiryDateTextField.setBounds(340, 360, 190, 30);
        add(expiryDateTextField);

        updateButton.setFont(new java.awt.Font("Arial", 1, 24));
        updateButton.setText("Update");
        updateButton.setBounds(310, 470, 120, 40);
        updateButton.addActionListener(e -> {
            String CNIC = cnicTextField.getText();
            String issueDate = issueDateTextField.getText();
            String expiryDate = expiryDateTextField.getText();
            String todays_date = get_todays_date_in_format();
            System.out.println(CNIC + " " + issueDate + " " + expiryDate + " " + todays_date);

            if (CNIC.length() != 13 || !CNIC.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "Invalid CNIC (must be 13 digits long)", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            client cl = new client();
            boolean exists = (boolean) cl.sendRequest("nadra.exists," + CNIC);
            // NADRA nadra = new NADRA();
            // if (nadra.exists(CNIC) == false) {
            if (exists == false) {
                JOptionPane.showMessageDialog(null, "CNIC does not exist in NADRA database", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!is_valid_date_format(issueDate) || !is_valid_date_format(expiryDate)) {
                JOptionPane.showMessageDialog(null, "Invalid date format (dd/MM/yyyy)", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!first_is_after_second(expiryDate, issueDate)) {
                JOptionPane.showMessageDialog(null, "Expiry date must be after issue date", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!date_is_before_today(expiryDate)) {
                JOptionPane.showMessageDialog(null, "Expiry date must be after today's date", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean flag = (boolean) cl.sendRequest("nadra.update," + CNIC + "," + issueDate + "," + expiryDate);

            // if (nadra.update(CNIC, issueDate, expiryDate) == true) {
            if (flag == true) {
                JOptionPane.showMessageDialog(null, "CNIC info updated successfully", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error: CNIC info not updated", "Error", JOptionPane.ERROR_MESSAGE);
            }
            cnicTextField.setText("");
            issueDateTextField.setText("");
            expiryDateTextField.setText("");
        });
        add(updateButton);
    }

    public static boolean date_is_before_today(String d) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate id = LocalDate.parse(d, f);
            LocalDate td = LocalDate.now();

            return !id.isBefore(td);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage().toString());
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

    public static String get_todays_date_in_format() {
        java.util.Date d = new java.util.Date();
        java.text.SimpleDateFormat frmt = new java.text.SimpleDateFormat(FORMAT);
        return frmt.format(d);
    }
}
