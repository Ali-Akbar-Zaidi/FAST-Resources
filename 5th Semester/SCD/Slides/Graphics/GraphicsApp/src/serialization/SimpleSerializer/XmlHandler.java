/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialization.SimpleSerializer;

import java.util.ArrayList;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author farooq
 */
public class XmlHandler extends DefaultHandler{

    
    ObjectNode currentObject;
    String currentValue;
    String rootId;


    Stack<String> types = new Stack<String>();
    ArrayList<ObjectNode> objects = new ArrayList<ObjectNode>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        if(qName.equals("SerializedStream")){
            rootId = attributes.getValue("root");
        }
        
        String type = attributes.getValue("type");

        if(type == null){
            return;
        }
        else if(type.equals("object")){
            currentObject = new ObjectNode(attributes.getValue("id"), qName);
        }

         types.push(type);

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException{
        currentValue = String.valueOf(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{

        try{

            String type = types.pop();

            if(type.equals("object")){
                objects.add(currentObject);
            }
            else if(type.equals("attribute")){
                currentObject.writeAttribute(qName, currentValue);
            }
            else if(type.equals("reference")){
                currentObject.writeReference(qName, currentValue);
            }
            
        }
        catch(Exception ex){

        }
        
    }

    public ArrayList<ObjectNode> getObjects(){
        return objects;
    }

    public String getRootId(){
        return rootId;
    }

}
