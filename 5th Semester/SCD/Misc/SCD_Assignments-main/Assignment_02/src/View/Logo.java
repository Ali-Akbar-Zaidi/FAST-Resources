package View;

import javax.swing.*;
import java.awt.*;

public class Logo {
    public Image getLogo() {
        ImageIcon logo = new ImageIcon("resources/logo.png");
        return logo.getImage();
    }

    public ImageIcon getLogoIcon() {
        ImageIcon logo = new ImageIcon("resources/logo.png");
        return logo;
    }
}
