/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics.serialization;

/**
 *
 * @author farooq
 */
public interface Serializer {

    public void serialize(java.io.Serializable object);
    public Object deserialize();

}
