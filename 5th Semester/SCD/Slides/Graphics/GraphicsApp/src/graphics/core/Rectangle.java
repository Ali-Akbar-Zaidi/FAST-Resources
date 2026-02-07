package graphics.core;

import graphics.data.IShapeDAO;
import graphics.data.ShapeDAO;
import java.util.Hashtable;
import serialization.SimpleSerializer.SimpleSerializedStream;


public class Rectangle extends Shape {
	
	protected Point topLeft; 	
	
	protected int length;   	
	protected int width;    	
								
	
	public Rectangle(int x,int y, int w, int l) throws Exception{
		super(x,y);
		topLeft = point;
		length = l;
		width = w;
	}
	
	public Point getTopLeft(){
		return topLeft;
	}
	
	public Point getTopRight(){
		return new Point(topLeft.getX() + width, topLeft.getY());
	}
	
	public Point getBottomLeft(){
		return new Point(topLeft.getX() , topLeft.getY() + length);
	}
	
	public Point getBottomRight(){
		return new Point(topLeft.getX() + width , topLeft.getY() + length);
	}
	
	public int perimeter(){
		return 2*length + 2*width;
	}
	
	public int area(){
		return length*width;
	}
	
	public void scaleX(int x){
		width += x;
	}
	
	public void scaleY(int y){
		length += y;
	}
	
	public void draw(){
		graphics.drawRect(topLeft.getX(), topLeft.getY(), width, length);
	}

        public String typeName(){
            return "Rectangle";
        }

        public void write(SimpleSerializedStream stream) {
            stream.writeAttribute(this, "width", String.valueOf(width));
            stream.writeAttribute(this, "length", String.valueOf(length));
            stream.writeObject(this, "topLeft", point);
       }               

        public void read(SimpleSerializedStream stream) {
            width = Integer.parseInt(stream.readAttribute(this, "width"));
            length = Integer.parseInt(stream.readAttribute(this, "length"));
            topLeft = (Point)  stream.readObject(this, "topLeft");
            point = topLeft;

        }

        public void save(){
            IShapeDAO dao = new ShapeDAO();
            Hashtable<String,String> attributes = new Hashtable<String, String>();
            attributes.put("width", String.valueOf(width));
            attributes.put("length", String.valueOf(length));
            dao.insertShape("rectangle", point, attributes);
        }

}
