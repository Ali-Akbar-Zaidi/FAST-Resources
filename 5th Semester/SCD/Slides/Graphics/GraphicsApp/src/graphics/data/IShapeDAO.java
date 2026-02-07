/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics.data;

import graphics.core.Point;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author farooq
 */
public interface IShapeDAO {

    public void insertShape(String type,Point point,Hashtable<String,String> shapeAttributes);
    public ArrayList<Hashtable<String,String>> getShapes();

}
