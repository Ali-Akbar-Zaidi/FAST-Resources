/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics;

import graphics.ui.GraphicsEditor;

/**
 * Main class for Graphics App
 *
 * @author farooq
 */
public class Main {

    /**
    * main method
    * 
    * @param args command-line arguments
    * 
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphicsEditor().setVisible(true);
            }
        });
    }

}
