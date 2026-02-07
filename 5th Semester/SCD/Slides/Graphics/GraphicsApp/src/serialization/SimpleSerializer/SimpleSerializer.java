/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialization.SimpleSerializer;

import java.io.FileWriter;
import graphics.serialization.Serializer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;


/**
 *
 * @author farooq
 */
public class SimpleSerializer  implements Serializer{

    private IObjectFactory factory;

    public SimpleSerializer(IObjectFactory factory){
        this.factory = factory;
    }

    public void serialize(Serializable obj){
        serialize((SimpleSerializable) obj);
    }


    public void serialize(SimpleSerializable object) {

        SimpleSerializedStream stream = new SimpleSerializedStream();        
        object.write(stream);
        stream.setRoot(object);

        String result = stream.toString();

        try{
            FileWriter writer = new FileWriter("/home/farooq/canvas.xml");
            writer.write(result);
            writer.close();
        }
        catch(Exception ex){

        }
    }

    public Object deserialize() {
        StringBuffer xml = new StringBuffer();

        try{
            BufferedReader reader = new BufferedReader(new FileReader("/home/farooq/canvas.xml"));
            while(reader.ready()){
                xml.append(reader.readLine());
            }

        }
        catch(Exception ex){

        }

        SimpleSerializedStream stream = new SimpleSerializedStream(factory);
        stream.parse(xml.toString());

        return stream.read();
    }



}
