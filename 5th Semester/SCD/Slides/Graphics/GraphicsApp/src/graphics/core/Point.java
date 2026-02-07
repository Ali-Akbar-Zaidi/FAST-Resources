package graphics.core;

import serialization.SimpleSerializer.SimpleSerializedStream;
import serialization.SimpleSerializer.SimpleSerializable;


public class Point implements java.io.Serializable,SimpleSerializable{
	
	private int x;
	private int y;
	
	public Point(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void moveHorizontally(int x){
		this.x = x;
	}
	
	public void moveVertically(int y){
		this.y = y;
	}
	
	public void move(int x,int y){
		moveHorizontally(x);
		moveVertically(y);
	}
	
	public Point clone(){
		return new Point(x,y);
	}
	
	public String getCoordinates(){
		return "(" + x + "," + y + ")" ;
	}

        public void write(SimpleSerializedStream stream ){
          
            stream.writeAttribute(this, "x", String.valueOf(x));
            stream.writeAttribute(this, "y", String.valueOf(y));
        
        }

        public void read(SimpleSerializedStream stream){
            x = Integer.parseInt(stream.readAttribute(this, "x"));
            y = Integer.parseInt(stream.readAttribute(this, "y"));
        }

        public String id(){
            return String.valueOf(this.hashCode());
        }

        public String typeName(){
            return "Point";
        }       

}
