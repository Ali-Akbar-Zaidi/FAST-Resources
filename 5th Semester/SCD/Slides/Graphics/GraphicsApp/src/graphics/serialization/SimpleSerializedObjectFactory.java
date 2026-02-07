/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics.serialization;

import graphics.core.Canvas;
import graphics.core.Circle;
import graphics.core.Point;
import graphics.core.Rectangle;
import graphics.core.Square;
import serialization.SimpleSerializer.SimpleSerializable;
import serialization.SimpleSerializer.IObjectFactory;

/**
 *
 * @author farooq
 */
public class SimpleSerializedObjectFactory  implements IObjectFactory{

    private static SimpleSerializedObjectFactory instance;

    public static SimpleSerializedObjectFactory getInstance(){
        if(instance == null){
            instance = new SimpleSerializedObjectFactory();
        }

        return instance;
    }

    public SimpleSerializable createObject(String name) {
        SimpleSerializable object = null;
        
        try{

            if(name.toLowerCase().equals("point")){
                object = new Point(0,0);
            }
            else if(name.toLowerCase().equals("circle")){
                object = new Circle(0,0,0);
            }
            else if(name.toLowerCase().equals("rectangle")){
                object = new Rectangle(0,0,0,0);
            }
            else if(name.toLowerCase().equals("square")){
                object = new Square(0,0,0);
            }
            else if(name.toLowerCase().equals("canvas")){
                object = new Canvas(null);
            }

        }
        catch(Exception ex){
            
        }

        return object;

    }


    

}

