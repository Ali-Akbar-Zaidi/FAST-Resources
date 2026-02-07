package graphics.core;

import graphics.data.IShapeDAO;
import graphics.data.ShapeDAO;
import java.util.Hashtable;
import serialization.SimpleSerializer.SimpleSerializedStream;
import serialization.SimpleSerializer.SimpleSerializable;


public class Circle extends Shape implements SimpleSerializable{
	
	protected int radius;
	
	public Circle(int x,int y, int r) throws Exception{
		super(x,y);
		radius = r;
	}

	@Override
	public int area() {
		return (int) (22/7 * radius * radius);
	}

	@Override
	public int perimeter() {
		return (int) (22/7 * radius * 2);
	}

	@Override
	public void scaleX(int x) {
		radius += x;
	}

	@Override
	public void scaleY(int y) {
		scaleX(y);
	}
	
	public void draw(){
		graphics.drawOval(point.getX() - radius/2, point.getY() - radius/2, radius, radius);
	}

        public String typeName(){
            return "Circle";
        }

        public void write(SimpleSerializedStream stream){
            
            stream.writeAttribute(this,"radius",String.valueOf(radius));
            stream.writeObject(this,"point",point);

        }

        public void read(SimpleSerializedStream stream){
            this.radius = Integer.parseInt(stream.readAttribute(this,"radius"));
            this.point = (Point) stream.readObject(this,"point");
        }

        public void save(){
            IShapeDAO dao = new ShapeDAO();
            Hashtable<String,String> attributes = new Hashtable<String, String>();
            attributes.put("radius", String.valueOf(radius));
            dao.insertShape("circle", point, attributes);
        }
        

}
