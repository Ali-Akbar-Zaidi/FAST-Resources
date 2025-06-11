package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.junit.Test;

import View.Dashboard;

public class Dashboard_Test {
    @Test
    public void testPaintBorder() {
        // Create an instance of RoundedBorder with a specific color and radius
        Dashboard.RoundedBorder border = new Dashboard().new RoundedBorder(Color.RED, 15);

        // Create a mock component and a graphics context
        JPanel mockPanel = new JPanel();
        BufferedImage mockImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = mockImage.createGraphics();

        try {
            // Attempt to paint the border
            border.paintBorder(mockPanel, g2d, 0, 0, 50, 50);
        } catch (Exception e) {
            fail("paintBorder threw an unexpected exception: " + e.getMessage());
        }

        // No assertions are required as we are only checking for exceptions during
        // execution
    }

    @Test
    public void testGetBorderInsetsDefault() {
        // Create an instance of RoundedBorder
        Dashboard.RoundedBorder border = new Dashboard().new RoundedBorder(Color.BLACK, 10);

        // Create a mock component
        JPanel mockPanel = new JPanel();

        // Get the border insets
        Insets insets = border.getBorderInsets(mockPanel);

        // Verify the insets are calculated correctly
        assertEquals("Top inset should be radius + 1", 11, insets.top);
        assertEquals("Left inset should be radius + 1", 11, insets.left);
        assertEquals("Bottom inset should be radius + 2", 12, insets.bottom);
        assertEquals("Right inset should be radius", 10, insets.right);
    }

    @Test
    public void testGetBorderInsetsCustom() {
        // Create an instance of RoundedBorder
        Dashboard.RoundedBorder border = new Dashboard().new RoundedBorder(Color.BLUE, 20);

        // Create a mock component and custom insets
        JPanel mockPanel = new JPanel();
        Insets customInsets = new Insets(0, 0, 0, 0);

        // Get the border insets with the custom Insets object
        Insets insets = border.getBorderInsets(mockPanel, customInsets);

        // Verify the custom insets were updated correctly
        assertEquals("Top inset should be radius + 1", 21, insets.top);
        assertEquals("Left inset should be radius + 1", 21, insets.left);
        assertEquals("Bottom inset should be radius + 2", 22, insets.bottom);
        assertEquals("Right inset should be radius", 22, insets.right);
    }
}
