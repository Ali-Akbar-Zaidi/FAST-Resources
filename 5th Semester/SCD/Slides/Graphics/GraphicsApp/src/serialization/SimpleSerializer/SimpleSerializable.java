/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialization.SimpleSerializer;

import java.util.Hashtable;

/**
 *
 * @author farooq
 */
public interface SimpleSerializable extends java.io.Serializable{

    public void write(SimpleSerializedStream stream);
    public void read(SimpleSerializedStream stream);
    public String id();
    public String typeName();
    
    
}
