/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package customcomponents;

import javax.swing.JFrame;

/**
 *
 * @author amyaw
 */
public class CustomComponents extends JFrame {
    
    public CustomComponents(){
        JStar star = new JStar();
        add(star);
        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CustomComponents frame = new CustomComponents();
        frame.setVisible(true);
    }
    
}
