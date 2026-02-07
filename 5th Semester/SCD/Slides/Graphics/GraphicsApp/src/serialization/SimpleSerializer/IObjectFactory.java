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
public interface IObjectFactory{

    public SimpleSerializable createObject(String name);

}
