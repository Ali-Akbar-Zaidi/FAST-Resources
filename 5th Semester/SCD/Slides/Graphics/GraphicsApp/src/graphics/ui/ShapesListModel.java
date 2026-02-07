/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics.ui;

import graphics.core.Shape;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author farooq
 */
public class ShapesListModel extends AbstractListModel {

    ArrayList<Shape> shapes;

    public ShapesListModel(){
        shapes = new ArrayList<Shape>();
    }

    public int getSize() {
        return this.shapes.size();
    }

    public Object getElementAt(int index) {
        return shapes.get(index);
    }

    public void addElement(Shape s){
        shapes.add(s);
        this.fireIntervalAdded(this, shapes.size() - 1, shapes.size() - 1);
    }



}
