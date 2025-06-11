package View;

import javax.swing.*;

import Controller.billing.Billing;

public class ViewBillReport extends JPanel {

    public JButton calculateButton;
    public JLabel titleLabel;
    public JLabel unpaidBillsLabel;
    public JLabel paidBillsLabel;
    public JPanel mainPanel;

    public ViewBillReport() {
        init();
    }

    private void init() {
        int paid = 0;
        int unpaid = 0;
        Billing b = new Billing();
        paid = b.get_reports_paid();
        unpaid = b.get_reports_unpaid();
        titleLabel = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        unpaidBillsLabel = new javax.swing.JLabel();
        paidBillsLabel = new javax.swing.JLabel();
        calculateButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(730, 700));
        setMinimumSize(new java.awt.Dimension(730, 700));
        setLayout(null);

        titleLabel.setFont(new java.awt.Font("Arial", 1, 24));
        titleLabel.setText("View Bill Report");
        titleLabel.setBounds(270, 70, 200, 30);
        add(titleLabel);

        mainPanel.setLayout(null);

        unpaidBillsLabel.setFont(new java.awt.Font("Arial", 1, 18));
        unpaidBillsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        unpaidBillsLabel.setText("There are a total of " + unpaid + " unpaid bills");
        unpaidBillsLabel.setBounds(120, 160, 300, 30);
        mainPanel.add(unpaidBillsLabel);

        paidBillsLabel.setFont(new java.awt.Font("Arial", 1, 18));
        paidBillsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        paidBillsLabel.setText("There are a total of " + paid + " paid bills");
        paidBillsLabel.setBounds(120, 100, 300, 30);
        mainPanel.add(paidBillsLabel);

        mainPanel.setBounds(100, 170, 530, 280);
        add(mainPanel);

        calculateButton.setFont(new java.awt.Font("Arial", 1, 18));
        calculateButton.setText("Recalculate");
        calculateButton.setBounds(300, 560, 150, 40);
        calculateButton.setFocusable(false);

        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Billing billing = new Billing();
                int paid = billing.get_reports_paid();
                int unpaid = billing.get_reports_unpaid();
                unpaidBillsLabel.setText("There are a total of " + unpaid + " unpaid bills");
                paidBillsLabel.setText("There are a total of " + paid + " paid bills");
            }
        });
        add(calculateButton);
    }
}
