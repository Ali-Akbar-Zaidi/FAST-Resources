package Tests;

import org.junit.Test;

import View.Logo;

import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;

public class Logo_Test {
    public ImageIcon logo_manual = new ImageIcon("resources/logo.png");

    @Test
    public void testGetLogo() {
        Logo logo = new Logo();
        Image image = logo.getLogo();

        // Check that the returned Image is not null
        assertNotNull("Logo image should not be null", image);

        // assert equals for same path to image
        assertEquals(logo_manual.getImage(), image);
    }

    @Test
    public void testGetLogoIcon() {
        Logo logo = new Logo();
        ImageIcon imageIcon = logo.getLogoIcon();

        // Check that the returned ImageIcon is not null
        assertNotNull("Logo ImageIcon should not be null", imageIcon);

        // Verify the ImageIcon has a valid Image
        assertNotNull("ImageIcon should contain a valid Image", imageIcon.getImage());
    }
}
