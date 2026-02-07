package graphics.core;

import graphics.data.IShapeDAO;
import graphics.data.ShapeDAO;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Hashtable;
import serialization.SimpleSerializer.SimpleSerializable;


public abstract class Shape implements java.io.Serializable,SimpleSerializable{
	
	protected transient Graphics graphics;
        protected Point point;
	
	public Shape(int x,int y) throws Exception{
//		point = new Point(x,y);
                point = PointsPool.getInstance().getPoint(x, y);
	}
	
	public void move(int x,int y) throws Exception{
                
                PointsPool.getInstance().releasePoint(point);
                point.move(x,y);
                PointsPool.getInstance().acquirePoint(point);
                
	}

        public void setGraphicsComponent(Graphics g){
            graphics = g;
        }

        public String toString(){
            return typeName() + " : " +  point.getCoordinates();
        }
	
	public abstract int perimeter();
	
	public abstract int area();
	
	public abstract void scaleX(int x);
	
	public abstract void scaleY(int y);
	
	public abstract void draw();

        public abstract String typeName();

        public String id(){
            return String.valueOf(this.hashCode());
        }

        public abstract void save();        
        

        public static ArrayList<Shape> loadShapes(){

            ArrayList<Shape> shapesCollection = new ArrayList<Shape> ();

            IShapeDAO dao = new ShapeDAO();
            ArrayList<Hashtable<String,String>> shapes = dao.getShapes();

            for(Hashtable<String,String> shape : shapes){
                shapesCollection.add(ShapeFactory.getInstance().createShape(shape));
            }

            return shapesCollection;
        }

}
