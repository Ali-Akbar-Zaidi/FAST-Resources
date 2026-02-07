/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customcomponents;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.imageio.*;

/**
 *
 * @author amyaw
 */
public class JStar extends JComponent {
    
    boolean state;
    
    public JStar(){
        state = false;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                if (!state){
                    state = true;
                }
                else{
                    state = false;
                }
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStar(g);
        /*g.drawRect(1, 1, 110, 91);
        try {
            BufferedImage img;
            if (state == false){
                img = ImageIO.read(new File("C:\\Users\\amyaw\\OneDrive\\Documents\\NetBeansProjects\\CustomComponents\\src\\customcomponents\\star-normal.png"));
            }
            else{
                img = ImageIO.read(new File("C:\\Users\\amyaw\\OneDrive\\Documents\\NetBeansProjects\\CustomComponents\\src\\customcomponents\\star-filled.png"));
            }
            g.drawImage(img, 2, 2, null);
        } catch (IOException ex) {
            Logger.getLogger(JStar.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    
    protected void drawStar(Graphics g){
        int[] x = {50,60,80,60,50,40,20,40};
        int[] y = {10,30,30,40,60,40,30,30};
        g.drawPolygon(x, y, 8);
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(110,91);
    }
    
    
    
}
