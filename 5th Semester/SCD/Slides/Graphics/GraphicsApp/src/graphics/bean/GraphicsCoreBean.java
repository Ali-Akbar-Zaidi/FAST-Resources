/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics.bean;

import java.io.Serializable;
import graphics.core.*;
import javax.swing.JPanel;

/**
 *
 * @author farooq
 */
public class GraphicsCoreBean implements Serializable {

     JPanel panel;
     Canvas canvas;

    public GraphicsCoreBean() {
        
    }
    
    public JPanel getDrawingArea(){
        return panel;
    }

    public void setDrawingArea(JPanel panel){
        this.panel = panel;        
    }

    public void drawRectangle(){

        canvas = new Canvas(panel.getGraphics());

        try{
            Rectangle rect = new Rectangle(100, 100, 100, 50);
            canvas.draw(rect);
        }
        catch(Exception ex){

        }
    }

    public void drawSquare(){

        canvas = new Canvas(panel.getGraphics());

        try{
            Square sq = new Square(100, 100, 100);
            canvas.draw(sq);
        }
        catch(Exception ex){

        }
    }

    public void drawCircle(){

        canvas = new Canvas(panel.getGraphics());
        
        try{
            Circle ci = new Circle(100, 100, 100);
            canvas.draw(ci);
        }
        catch(Exception ex){

        }
    }

}
