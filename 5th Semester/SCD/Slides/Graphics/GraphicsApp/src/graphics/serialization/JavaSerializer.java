/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author farooq
 */
public class JavaSerializer implements Serializer{
    private final String filepath = "C:\\Users\\farooq\\Documents\\NetBeansProjects\\GraphicsApp\\canvas.dat";
    
    public void serialize(Serializable object) {
        try{
                ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filepath));
                stream.writeObject(object);
            }
            catch(Exception ex){

            }
    }

    public Object deserialize() {
        Object obj = null;
        try{
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filepath));
                obj = stream.readObject();
            }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return obj;
    }


}
