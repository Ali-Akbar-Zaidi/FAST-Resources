package graphics.core;

import graphics.data.ShapeDAO;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import serialization.SimpleSerializer.SimpleSerializable;
import serialization.SimpleSerializer.SimpleSerializedStream;
import graphics.serialization.Serializer;
import graphics.serialization.SerializerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
//import graphics.serialization.SerializerFactory;
import java.util.Hashtable;

public class Canvas implements java.io.Serializable,SimpleSerializable{
      

        transient Graphics graphicsComponent;

        ArrayList<Shape> shapesCollection;

        public Canvas(Graphics g){
            graphicsComponent = g;
            shapesCollection = new ArrayList<Shape>();
        }
	
	public void draw(Shape s){
                shapesCollection.add(s);
                s.setGraphicsComponent(graphicsComponent);
		s.draw();
	}

        public void repaint(){
                for(Shape s : shapesCollection){
                    s.draw();
                }
        }

        public Iterator<Shape> getIterator(){

            return shapesCollection.iterator();

        }

        public void save(){
            /*            
            Serializer serializer = SerializerFactory.createSerializer();
            serializer.serialize(this);
            */
            
            
            for(Shape s : shapesCollection){
                s.save();
            }
            
            

        }

        public void load(){
            //Serializer serializer = SerializerFactory.createSerializer();
            //Canvas obj = (Canvas) serializer.deserialize();
            //this.shapesCollection = obj.shapesCollection;
            
            shapesCollection = Shape.loadShapes();

            for (Shape shape : shapesCollection){
                shape.setGraphicsComponent(graphicsComponent);
            }
    
        }

    public void write(SimpleSerializedStream stream) {

        for(Shape s: shapesCollection){
            stream.writeObject(this, "shape", s);
        }

    }

    public void read(SimpleSerializedStream stream) {
        ArrayList<SimpleSerializable> objectsList = stream.readObjectsList(this,"shape");

        for(SimpleSerializable object : objectsList){
            shapesCollection.add((Shape) object);
        }
        
    }

    public String id() {
        return String.valueOf(this.hashCode());
    }

    public String typeName() {
        return "Canvas";
    }

    
}
