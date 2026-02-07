package graphics.core;

import graphics.data.IShapeDAO;
import graphics.data.ShapeDAO;
import java.util.Hashtable;


public class Square extends Rectangle {
	
	public Square(int x,int y, int s) throws Exception{
		super(x,y,s,s);
	}
	
	public void scaleX(int x){
		super.scaleX(x);
		length = width;   		
		
	}
	
	public void scaleY(int y){
		super.scaleY(y);
		width = length;	
		
	}
	
	public void draw(){
		super.draw();
	}

        public String typeName(){
            return "Square";
        }

        public void save(){
            IShapeDAO dao = new ShapeDAO();
            Hashtable<String,String> attributes = new Hashtable<String, String>();
            attributes.put("size", String.valueOf(width));
            dao.insertShape("square", point, attributes);
        }

        
}
