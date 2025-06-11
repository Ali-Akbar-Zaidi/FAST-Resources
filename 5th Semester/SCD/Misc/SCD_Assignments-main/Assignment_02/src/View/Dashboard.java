package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard extends JFrame {
    private JPanel rightPanel;
    private CardLayout cardLayout;

    public Dashboard() {
        setTitle("Employee Dashboard");
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

        JButton new_meter_btt = createMenuButton("Add New Meter");
        menuPanel.add(new_meter_btt);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        JButton update_cust_info_btt = createMenuButton("Update Customer Info");
        menuPanel.add(update_cust_info_btt);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        JButton create_this_month_bill_btt = createMenuButton("Create This Month Bill");
        menuPanel.add(create_this_month_bill_btt);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        JButton pay_bills_btt = createMenuButton("Pay Bills");
        menuPanel.add(pay_bills_btt);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        JButton update_tax_info_btt = createMenuButton("Update Tax Info");
        menuPanel.add(update_tax_info_btt);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        JButton view_bill_btt = createMenuButton("View Bill");
        menuPanel.add(view_bill_btt);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        JButton view_bill_report_btt = createMenuButton("View Bill Report");
        menuPanel.add(view_bill_report_btt);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        JButton see_expired_cnic_btt = createMenuButton("See Expired CNIC");
        menuPanel.add(see_expired_cnic_btt);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        JButton edit_files_btt = createMenuButton("Edit Files");
        menuPanel.add(edit_files_btt);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        JButton logout_btt = createMenuButton("Logout");
        menuPanel.add(logout_btt);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        menuPanel.setVisible(true);

        // Create right panel with CardLayout
        rightPanel = new JPanel();
        cardLayout = new CardLayout();
        rightPanel.setLayout(cardLayout);

        // Add different panels to the right panel
        rightPanel.add(new NewMeter(), "NewMeter");
        rightPanel.add(new UpdateCustomerData(), "UpdateCustomerInfo");
        rightPanel.add(new CreateMonthsBill(), "CreateThisMonthBill");
        rightPanel.add(new PayBill(), "PayBills");
        rightPanel.add(new UpdateTaxInfo(), "UpdateTaxInfo");
        rightPanel.add(new ViewBill(), "ViewBill");
        rightPanel.add(new ViewBillReport(), "ViewBillReport");
        rightPanel.add(new SeeExpiredCNIC(), "SeeExpiredCNIC");
        rightPanel.add(new EditFiles(), "EditFiles");

        // Add action listeners to buttons
        new_meter_btt.addActionListener(e -> cardLayout.show(rightPanel, "NewMeter"));
        update_cust_info_btt.addActionListener(e -> cardLayout.show(rightPanel, "UpdateCustomerInfo"));
        create_this_month_bill_btt.addActionListener(e -> cardLayout.show(rightPanel, "CreateThisMonthBill"));
        pay_bills_btt.addActionListener(e -> cardLayout.show(rightPanel, "PayBills"));
        update_tax_info_btt.addActionListener(e -> cardLayout.show(rightPanel, "UpdateTaxInfo"));
        view_bill_btt.addActionListener(e -> cardLayout.show(rightPanel, "ViewBill"));
        view_bill_report_btt.addActionListener(e -> cardLayout.show(rightPanel, "ViewBillReport"));
        see_expired_cnic_btt.addActionListener(e -> cardLayout.show(rightPanel, "SeeExpiredCNIC"));
        edit_files_btt.addActionListener(e -> cardLayout.show(rightPanel, "EditFiles"));
        logout_btt.addActionListener(e -> {
            dispose();
            new EmployeeLogin();
        });

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
        new Dashboard();
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