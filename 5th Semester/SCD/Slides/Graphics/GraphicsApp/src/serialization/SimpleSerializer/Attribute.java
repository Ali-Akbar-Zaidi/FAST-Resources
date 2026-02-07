/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialization.SimpleSerializer;

/**
 *
 * @author farooq
 */
public class Attribute {

    protected String name;
    protected String value;

    public Attribute(String name,String value){
        this.name = name;
        this.value = value;
    }

    public String toString(){        
        return ( "<" + name + " type='attribute'>" + value + "</" + name + ">" );
    }

}
