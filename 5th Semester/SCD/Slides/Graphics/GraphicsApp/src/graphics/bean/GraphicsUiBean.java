/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics.bean;

import java.io.Serializable;
import java.awt.Graphics;
import graphics.core.*;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author farooq
 */
public class GraphicsUiBean extends JPanel implements Serializable {

    Canvas canvas;

    public GraphicsUiBean() {

       this.setBackground(Color.white);
        
    }
    
    public void drawRectangle(){

        canvas = new Canvas(getGraphics());

        try{
            Rectangle rect = new Rectangle(100, 100, 100, 50);
            canvas.draw(rect);
        }
        catch(Exception ex){

        }
    }

    public void drawSquare(){

        canvas = new Canvas(getGraphics());

        try{
            Square sq = new Square(100, 100, 100);
            canvas.draw(sq);
        }
        catch(Exception ex){

        }
    }

    public void drawCircle(){

        canvas = new Canvas(getGraphics());
        
        try{
            Circle ci = new Circle(100, 100, 100);
            canvas.draw(ci);
        }
        catch(Exception ex){

        }
    }

}
