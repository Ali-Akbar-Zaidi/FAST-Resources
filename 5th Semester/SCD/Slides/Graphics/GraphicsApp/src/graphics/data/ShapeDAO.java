/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics.data;

import graphics.core.Point;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.UUID;

/**
 *
 * @author farooq
 */
public class ShapeDAO implements IShapeDAO{

    public ArrayList<Hashtable<String,String>> getShapes(){
        ArrayList<Hashtable<String,String>> shapesList = new ArrayList<Hashtable<String, String>>();

        try{

            String query = "SELECT shapeid, s.type, p.x, p.y, a.name, a.value FROM attributes a, point p, shape s WHERE a.shapeid = s.id AND s.pointid = p.id";
            ResultSet rs = DataAccessHelper.executeQuery(query);

            Hashtable<String,Hashtable<String,String>> shapes = new Hashtable<String, Hashtable<String, String>>();

            // read result set
            while(rs.next()){
                String shapeId = rs.getString("shapeid");
                Hashtable<String,String> shape = getShapeHashtable(shapeId, shapes);

                shape.put("type", rs.getString("type"));
                shape.put("x", rs.getString("x"));
                shape.put("y", rs.getString("y"));                
                shape.put(rs.getString("name"),rs.getString("value"));
            }

            // add to shapesList            
            for (Hashtable<String,String> shape : shapes.values()){
                shapesList.add(shape);
            }
        }
        catch(Exception ex){

        }

        return shapesList;
    }

    private Hashtable<String,String> getShapeHashtable(String shapeId,Hashtable shapes){
        Hashtable<String,String> shape = (Hashtable<String,String>) shapes.get(shapeId);

        if(shape == null){
            shape = new Hashtable<String,String>();
            shapes.put(shapeId, shape);
        }

        return shape;
    }


    public void insertShape(String type,Point point,Hashtable<String,String> shapeAttributes){

        // query for point
        String pointId = getUniqueId();
        String sql = "insert into point(id,x,y) values ('" + pointId + "','" + point.getX() + "','" + point.getY() + "');";
        DataAccessHelper.execute(sql, new ArrayList(), new ArrayList());

        // query for shape
        String shapeId = getUniqueId();
        sql = "insert into shape(id,type,pointid) values ('" + shapeId + "','" + type + "','" + pointId + "');";
        DataAccessHelper.execute(sql, new ArrayList(), new ArrayList());

        // query for attributes
        Enumeration<String> attributes = shapeAttributes.keys();
        while(attributes.hasMoreElements()){

            String attrId = getUniqueId();
            String attribute = attributes.nextElement();

            sql = "insert into attributes (id,shapeid,name,type,value) values " +
                    "('" + attrId + "','" + shapeId + "','" + attribute + "','','" + shapeAttributes.get(attribute) + "');";

            DataAccessHelper.execute(sql, new ArrayList(), new ArrayList());

        }

        

    }

    private String getUniqueId(){
        String id = UUID.randomUUID().toString();
        return id.replaceAll("-", "");
    }



}
