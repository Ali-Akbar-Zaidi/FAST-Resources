package Tests;

import org.junit.Test;

import View.MenuPanel;

import static org.junit.Assert.*;
import java.awt.*;

public class MenuPanel_Test {

    @Test
    public void testMenuPanelInitialization() {
        // Create an instance of MenuPanel
        MenuPanel menuPanel = new MenuPanel();

        // Verify that the background color is set correctly
        Color expectedBackgroundColor = Color.decode("#e5e7eb"); // The gray color
        assertEquals("Background color should be #e5e7eb", expectedBackgroundColor, menuPanel.getBackground());

        // Verify that the panel is visible
        assertTrue("MenuPanel should be visible", menuPanel.isVisible());
    }

    @Test
    public void testInitComponentsMethod() {
        // Create an instance of MenuPanel
        MenuPanel menuPanel = new MenuPanel();

        // Call initComponents explicitly (redundant for this test but ensures
        // correctness)
        menuPanel.initComponents();

        // Verify that the background color is still set correctly
        Color expectedBackgroundColor = Color.decode("#e5e7eb"); // The gray color
        assertEquals("Background color should still be #e5e7eb after initialization", expectedBackgroundColor,
                menuPanel.getBackground());

        // Verify that the panel is still visible
        assertTrue("MenuPanel should still be visible after initialization", menuPanel.isVisible());
    }
}
