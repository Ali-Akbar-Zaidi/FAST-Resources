import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

class myFrame extends JFrame {
    final int width = 800, height = 600;
    int currX = width / 2, currY = (height - 50) / 2;

    public myFrame() {
        setSize(800, 600);
        setLayout(new BorderLayout(10, 20));
        JButton north = new JButton("North");
        JButton south = new JButton("south");
        JButton east = new JButton("east");
        JButton west = new JButton("west");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.add(north);
        panel.add(south);
        panel.add(east);
        panel.add(west);
        panel.setBorder(new LineBorder(Color.black, 2));
        panel.setPreferredSize(new Dimension(width, height - (height - 50)));

        MyX Xstr = new MyX();
        Xstr.setBounds(0, 0, getWidth(), getHeight());

        JPanel centeredPanel = new JPanel(null);
        centeredPanel.setBackground(Color.WHITE);
        centeredPanel.setOpaque(true);
        centeredPanel.add(Xstr);
      
        add(centeredPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        setVisible(true);

        north.addActionListener(e -> {
            currY -= 12;
            Xstr.repaint();
            MyLine line = new MyLine(currX, currY + 12, currX, currY);
            line.setBounds(0, 0, width, height);
            centeredPanel.add(line);
            centeredPanel.revalidate();
            centeredPanel.repaint();

            
        });
              south.addActionListener(e -> {
            currY += 12;
            Xstr.repaint();
            MyLine line = new MyLine(currX, currY - 12, currX, currY);
            line.setBounds(0, 0, width, height);
            centeredPanel.add(line);
            centeredPanel.revalidate();
            centeredPanel.repaint();

            
        });
              east.addActionListener(e -> {
            currX += 12;
            Xstr.repaint();
            MyLine line = new MyLine(currX-12, currY, currX, currY);
            line.setBounds(0, 0, width, height);
            centeredPanel.add(line);
            centeredPanel.revalidate();
            centeredPanel.repaint();

            
        });
              west.addActionListener(e -> {
            currX -= 12;
            Xstr.repaint();
            MyLine line = new MyLine(currX+12, currY , currX, currY);
            line.setBounds(0, 0, width, height);
            centeredPanel.add(line);
            centeredPanel.revalidate();
            centeredPanel.repaint();

            
        });

    }

    private class MyX extends JComponent {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.red);
            g.drawString("X", currX, currY);
        }

    }

    private class MyLine extends JComponent {
        int x1, y1, x2, y2;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.black);
            g.drawLine(x1, y1, x2, y2);
        }

        public MyLine(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
    }
}

public class anotherPaint {
    public static void main(String[] arghs) {
        new myFrame();
    }
}
