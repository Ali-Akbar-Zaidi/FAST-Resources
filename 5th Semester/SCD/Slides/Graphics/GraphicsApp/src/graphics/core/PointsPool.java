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
public class PointsPool {

    private Hashtable<String,Point> pool = new Hashtable<String, Point>();
    private static PointsPool instance;

    private PointsPool() { }

    public static PointsPool getInstance(){
        if(instance == null){
            instance = new PointsPool();
        }
        return instance;
    }

    public Point getPoint(int x,int y) throws Exception{
        Point p = null;
        p = pool.get(getCoordinateForm(x, y));

        if(p == null){
            p = new Point(x,y);
            pool.put(getCoordinateForm(x, y), p);

        
            
        }
        
        return p;
            
    //    else throw new Exception("Point not available");

    }

    public  String getCoordinateForm(int x,int y){
        return "(" + x + "," + y + ")";
    }

    public void releasePoint(Point p){
        pool.remove(getCoordinateForm(p.getX(), p.getY()));
    }

    public void acquirePoint(Point p) throws Exception{
        String coordinates = getCoordinateForm(p.getX(), p.getY());
        
        if(pool.get(coordinates) != null){
            throw new Exception("Point not available");
        }
        else pool.put(coordinates, p);

    }






}
