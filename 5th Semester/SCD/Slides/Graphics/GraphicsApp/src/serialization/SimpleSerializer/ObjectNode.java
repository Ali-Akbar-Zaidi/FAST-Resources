/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialization.SimpleSerializer;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author farooq
 */
public class ObjectNode {

    String id;
    String name;
    ArrayList<Attribute> attributes;
    

    public ObjectNode(String id,String name){
        this.id = id;
        this.name = name;
        attributes = new ArrayList<Attribute>();    
    }

    public void writeAttribute(String name,String value){
        attributes.add(new Attribute(name, value));
    }

    public void writeReference(String name,String value){
        attributes.add(new Reference(name, value));
    }

    
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("<" + name + " id='" + id + "' type='object'>");

        buffer.append(getAttributesString());

        buffer.append("</" + name + ">");

        return buffer.toString();
    }

    private String getAttributesString(){

        StringBuffer buffer = new StringBuffer();

        Hashtable<String,Boolean> attributesWritten = new Hashtable<String, Boolean>();
        
        for(Attribute attr : attributes){
            String representation = attr.toString();

            if( attributesWritten.get(representation) == null){
                buffer.append(representation);           
                attributesWritten.put(representation, Boolean.TRUE);
            }

        }

        return buffer.toString();
    }

    public String readAttribute(String name){
        for(Attribute attribute : attributes){
            if(attribute.name.equals(name)){
                return attribute.value;
            }
        }

        return null;
    }

    public ArrayList<String> readAttributesList(String name)
    {

        ArrayList<String> list = new ArrayList<String>();

        for(Attribute attribute : attributes){
            if(attribute.name.equals(name)){
                list.add(attribute.value);
            }
        }

        return list;
    }

        
}
