/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics.serialization;

import serialization.SimpleSerializer.SimpleSerializer;

/**
 *
 * @author farooq
 */
public class SerializerFactory {

    public static Serializer createSerializer(){
        //return new SimpleSerializer(new SimpleSerializedObjectFactory());
   //     return new XstreamSerializer();
        return new JavaSerializer();
    }

}
