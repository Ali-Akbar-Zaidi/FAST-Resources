/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author farooq
 */
public class Reflection {

    public static void main(String args[]){

        try{
            String className = "";
            System.out.println("Enter class name");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            className = reader.readLine();

            Class objClass = Class.forName(className);

            System.out.println("\nFields:");
            Field [] fields = objClass.getFields();
            for(int i=0; i < fields.length; i++){
                System.out.println(fields[i].toString());
            }
            fields = objClass.getDeclaredFields();
            for(int i=0; i < fields.length; i++){
                System.out.println(fields[i].toString());
            }


            System.out.println("\nMethods:");
            Method [] methods = objClass.getMethods();
            for(int i=0; i < methods.length; i++){
                System.out.println(methods[i].toString());
            }

            System.out.println("\nInherits from: " + objClass.getSuperclass().getName());


        }
        catch(Exception ex){

        }

    }

}
