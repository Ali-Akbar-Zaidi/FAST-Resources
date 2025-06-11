package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerMenu extends JFrame {
    private JPanel rightPanel;
    private CardLayout cardLayout;

    public CustomerMenu() {
        setTitle("Customer Dashboard");
        setSize(1000, 700); // 1000 x 700
        setResizable(false);
        setIconImage(new Logo().getLogo());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setPreferredSize(new Dimension((int) (getWidth() * 0.27), getHeight())); // 27% of the width

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // add top and bottom padding
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton update_cnic_bttn = createMenuButton("Update CNIC");
        menuPanel.add(update_cnic_bttn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons
        // on click of this button, show the update CNIC panel
        update_cnic_bttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(rightPanel, "update_cnic");
            }
        });

        JButton view_bill_bttn = createMenuButton("Check Bill");
        menuPanel.add(view_bill_bttn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons
        // on click of this button, show the view bill panel
        view_bill_bttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(rightPanel, "view_bill");
            }
        });

        JButton view_estimate_btt = createMenuButton("View Estimate");
        menuPanel.add(view_estimate_btt);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons
        // on click of this button, show the view estimate panel
        view_estimate_btt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(rightPanel, "view_estimate");
            }
        });

        JButton go_back_btt = createMenuButton("Go Back");
        menuPanel.add(go_back_btt);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        menuPanel.setVisible(true);

        // Create right panel with CardLayout
        rightPanel = new JPanel();
        cardLayout = new CardLayout();
        rightPanel.setLayout(cardLayout);

        go_back_btt.addActionListener(e -> {
            dispose();
            new EmployeeLogin();
        });

        UpdateCNIC updateCNIC = new UpdateCNIC();
        rightPanel.add(updateCNIC, "update_cnic");

        CheckBill checkBill = new CheckBill();
        rightPanel.add(checkBill, "view_bill");

        ViewEstimate viewEstimate = new ViewEstimate();
        rightPanel.add(viewEstimate, "view_estimate");

        add(menuPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(true);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(new RoundedBorder(Color.BLACK, 10)); // Rounded border with a radius of 10
        button.setPreferredSize(new Dimension(200, 40));
        button.setMaximumSize(new Dimension(200, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
                button.setBorder(new RoundedBorder(Color.WHITE, 10));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
                button.setBorder(new RoundedBorder(Color.BLACK, 10));
            }
        });

        return button;
    }

    public static void main(String[] args) {
        new CustomerMenu();
    }
}

class RoundedBorder extends AbstractBorder {
    private Color color;
    private int radius;

    RoundedBorder(Color color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(color);
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = this.radius + 1;
        insets.right = insets.bottom = this.radius + 2;
        return insets;
    }
}