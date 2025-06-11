package Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import View.CustomerMenu;
import View.CustomerMenu.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CustomerMenu_Test {

    @Test
    public void testPaintBorder() {
        // Create a mock component and a graphics context
        RoundedBorder border = new CustomerMenu().new RoundedBorder(Color.RED, 15);
        JPanel panel = new JPanel();
        Graphics2D g2d = (Graphics2D) new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB).getGraphics();

        // Ensure no exceptions occur while painting
        try {
            border.paintBorder(panel, g2d, 0, 0, 50, 50);
        } catch (Exception e) {
            fail("paintBorder should not throw an exception: " + e.getMessage());
        }
    }

    @Test
    public void testGetBorderInsetsDefault() {
        RoundedBorder border = new CustomerMenu().new RoundedBorder(Color.BLACK, 10);
        JPanel panel = new JPanel();

        Insets insets = border.getBorderInsets(panel);

        // Verify the insets
        assertEquals("Insets top should be radius + 1", 11, insets.top);
        assertEquals("Insets left should be radius + 1", 11, insets.left);
        assertEquals("Insets bottom should be radius + 2", 12, insets.bottom);
        assertEquals("Insets right should be radius", 10, insets.right);
    }

    @Test
    public void testGetBorderInsetsWithCustomInsets() {
        RoundedBorder border = new CustomerMenu().new RoundedBorder(Color.BLUE, 20);
        JPanel panel = new JPanel();
        Insets customInsets = new Insets(0, 0, 0, 0);

        Insets insets = border.getBorderInsets(panel, customInsets);

        // Verify the insets were updated
        assertEquals("Custom Insets top should be radius + 1", 21, insets.top);
        assertEquals("Custom Insets left should be radius + 1", 21, insets.left);
        assertEquals("Custom Insets bottom should be radius + 2", 22, insets.bottom);
        assertEquals("Custom Insets right should be radius", 22, insets.right);
    }
}
