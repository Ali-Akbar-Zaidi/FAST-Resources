package View;

import javax.swing.*;
import Controller.authentication.Employee;

public class EmployeeSignUp extends javax.swing.JFrame {

    public JButton signUpButton;
    public JButton goToLoginButton;
    public JLabel signUpLabel;
    public JLabel usernameLabel;
    public JLabel passwordLabel;
    public JPanel mainPanel;
    public JTextField usernameTextField;
    public JTextField passwordTextField;

    public EmployeeSignUp() {
        init();
    }

    private void init() {

        mainPanel = new javax.swing.JPanel();
        signUpLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JTextField();
        signUpButton = new javax.swing.JButton();
        goToLoginButton = new javax.swing.JButton();
        setSize(499, 600);
        setTitle("Employee Sign Up");
        setIconImage(new Logo().getLogo());
        setLocationRelativeTo(null);
        setBounds(500, 100, 499, 600);
        setResizable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 700));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setFont(new java.awt.Font("Arial", 0, 12));
        mainPanel.setLayout(null);

        signUpLabel.setFont(new java.awt.Font("Arial", 1, 24));
        signUpLabel.setText("Sign Up");
        signUpLabel.setBounds(210, 70, 100, 30);
        mainPanel.add(signUpLabel);

        usernameLabel.setFont(new java.awt.Font("Arial", 1, 18));
        usernameLabel.setText("Username:");
        usernameLabel.setBounds(90, 190, 100, 30);
        mainPanel.add(usernameLabel);

        usernameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameTextField.setBounds(210, 190, 180, 30);
        mainPanel.add(usernameTextField);

        passwordLabel.setFont(new java.awt.Font("Arial", 1, 18));
        passwordLabel.setText("Password:");
        passwordLabel.setBounds(90, 260, 100, 30);
        mainPanel.add(passwordLabel);

        passwordTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordTextField.setBounds(210, 260, 180, 30);
        mainPanel.add(passwordTextField);

        signUpButton.setFont(new java.awt.Font("Arial", 1, 18));
        signUpButton.setText("SignUp");
        signUpButton.setBounds(200, 360, 100, 30);
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();

                if (username.length() < 7) {
                    JOptionPane.showMessageDialog(mainPanel, "Username must be at least 7 characters long", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password.length() < 7) {
                    JOptionPane.showMessageDialog(mainPanel, "Password must be at least 7 characters long", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Employee employee = new Employee();
                if (!employee.add_employee(username, password)) {
                    JOptionPane.showMessageDialog(mainPanel, "Error: Employee not added", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Employee added successfully", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                // remove data from fields
                usernameTextField.setText("");
                passwordTextField.setText("");

            }
        });
        mainPanel.add(signUpButton);

        goToLoginButton.setText("Go to Employee Login");
        goToLoginButton.setBounds(150, 490, 200, 30);
        goToLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new EmployeeLogin().setVisible(true);
                dispose();
            }
        });
        mainPanel.add(goToLoginButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

    }
}
