/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics.core;

import java.util.Hashtable;

/**
 *
 * @author farooq
 */
public class ShapeFactory {

    private static ShapeFactory instance = null;

    private ShapeFactory(){ }

    public static ShapeFactory getInstance(){
        if (instance == null){
            instance = new ShapeFactory();
        }

        return instance;
    }

    public Shape createShape(Hashtable<String,String> info){

        String type = (String )info.get("type");
        
        Shape shape = null;
        try{

            if(type.equals("circle")){

                shape = new Circle(Integer.parseInt(info.get("x").strip()),
                                                  Integer.parseInt(info.get("y").strip()),
                                                  Integer.parseInt(info.get("radius").strip())) ;
            }
            else if(type.equals("rectangle")){
                shape = new Rectangle(Integer.parseInt(info.get("x").strip()),
                                                      Integer.parseInt(info.get("y").strip()),
                                                      Integer.parseInt(info.get("width").strip()),
                                                      Integer.parseInt(info.get("length").strip()));
            }
            else if(type.equals("square")){
                shape = new Square(Integer.parseInt(info.get("x").strip()),
                                                      Integer.parseInt(info.get("y").strip()),
                                                      Integer.parseInt(info.get("size").strip()));
            }

        }
        catch(Exception ex){
            int x = 0;
        }

        return shape;

    }

}
