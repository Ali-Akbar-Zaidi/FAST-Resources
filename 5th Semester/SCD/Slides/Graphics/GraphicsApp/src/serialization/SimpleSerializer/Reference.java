/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialization.SimpleSerializer;

/**
 *
 * @author farooq
 */
public class Reference extends Attribute{

    public Reference(String name,String value){
        super(name,value);
    }

    public String toString(){
        return ( "<" + name + " type='reference'>" + value + "</" + name + ">" );
    }

}
