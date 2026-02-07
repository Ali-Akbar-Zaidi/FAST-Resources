/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package serialization.SimpleSerializer;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;


/**
 *
 * @author farooq
 */
public class SimpleSerializedStream {

    ObjectNode root;

    // serialization constructs
    Hashtable<String,ObjectNode> nodesMap;

    // deserialization constructs
    Hashtable<String,SimpleSerializable> objectsMap;
    Hashtable<String,String> objectsNodesIdMap;
    IObjectFactory factory;

    public SimpleSerializedStream(){
        nodesMap = new Hashtable<String, ObjectNode>();
        objectsMap = new Hashtable<String, SimpleSerializable>();
        objectsNodesIdMap = new Hashtable<String, String>();
    }
    
    
    public SimpleSerializedStream(IObjectFactory factory){
        nodesMap = new Hashtable<String, ObjectNode>();
        objectsMap = new Hashtable<String, SimpleSerializable>();
        objectsNodesIdMap = new Hashtable<String, String>();
        this.factory = factory;
    }

    public void setRoot(SimpleSerializable obj){
        root = nodesMap.get(obj.id());
    }

    private void createNode(SimpleSerializable obj){
                
        if(nodesMap.get( obj.id()) == null  ){
            nodesMap.put(obj.id(), new ObjectNode(obj.id(),obj.typeName() ));
        }
        
    }

    public void writeAttribute(SimpleSerializable sourceObject, String attribute,String value){

        createNode(sourceObject);

        ObjectNode node = nodesMap.get(sourceObject.id());
        node.writeAttribute(attribute, value);

    }

    public void writeObject(SimpleSerializable sourceObject, String attribute,SimpleSerializable value){

        createNode(sourceObject);

        ObjectNode node = nodesMap.get(sourceObject.id());
        node.writeReference(attribute, value.id());
        value.write(this);
        
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("<SerializedStream root='" + root.id +"'>");

        Enumeration<ObjectNode> enumeration =  nodesMap.elements();
        while(enumeration.hasMoreElements()){
            ObjectNode node = enumeration.nextElement();
            buffer.append(node.toString());
        }

        buffer.append("</SerializedStream>");

        return buffer.toString();
    }

    public void parse(String data){

        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            StringReader reader = new StringReader(data);
            XmlHandler handler = new XmlHandler();
            parser.parse(new InputSource(reader), handler);
            

            nodesMap.clear();
            ArrayList<ObjectNode> nodes = handler.getObjects();
            for(ObjectNode node : nodes){
                nodesMap.put(node.id, node);
            }

            root = nodesMap.get(handler.getRootId());

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public String readAttribute(SimpleSerializable sourceObject,String name){

        String id = objectsNodesIdMap.get(sourceObject.id());
        ObjectNode node = nodesMap.get(id);
        return node.readAttribute(name);

    }

    public ArrayList<String> readAttributesList(SimpleSerializable sourceObject,String name){
        String id = objectsNodesIdMap.get(sourceObject.id());
        ObjectNode node = nodesMap.get(id);
        return node.readAttributesList(name);
    }

    private void createObject(String id){

        SimpleSerializable object = factory.createObject(nodeType(id));
        objectsNodesIdMap.put(object.id(),id);
        objectsMap.put(id, object);
        
    }

    public SimpleSerializable read(){
        createObject(root.id);
        SimpleSerializable object = objectsMap.get(root.id);
        object.read(this);
        return object;
    }

    public SimpleSerializable read(String name){

        Enumeration<ObjectNode> nodesEnumeration = nodesMap.elements();

        while(nodesEnumeration.hasMoreElements()){
            ObjectNode node = nodesEnumeration.nextElement();

            if(node.name.equals(name)){
                createObject(node.id);
                SimpleSerializable object = objectsMap.get(node.id);
                
                object.read(this);
                return object;
            }
        }

        return null;

    }

    public SimpleSerializable readObject(SimpleSerializable obj,String name){

        String referenceId = readAttribute(obj, name);
        SimpleSerializable object = objectsMap.get(referenceId);

        if(object == null){
            createObject(referenceId);
            object = objectsMap.get(referenceId);
            object.read(this);
        }

        return object;        
    }

    public ArrayList<SimpleSerializable> readObjectsList(SimpleSerializable obj,String name){

        ArrayList<SimpleSerializable> objectsList = new ArrayList<SimpleSerializable>();

        ArrayList<String> referenceIdList = readAttributesList(obj, name);

        for(String referenceId : referenceIdList){
            SimpleSerializable object = objectsMap.get(referenceId);

            if(object == null){
                createObject(referenceId);
                object = objectsMap.get(referenceId);
                object.read(this);
            }

            objectsList.add(object);
        }

        return objectsList;
    }


    private String nodeType(String id){
        Enumeration<ObjectNode> nodesEnumeration = nodesMap.elements();

        while(nodesEnumeration.hasMoreElements()){
            ObjectNode node = nodesEnumeration.nextElement();

            if(node.id.equals(id)){
                return node.name;
            }
        }

        return null;
    }
    
}
