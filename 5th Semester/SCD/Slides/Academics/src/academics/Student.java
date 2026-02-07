/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academics;

import java.util.ArrayList;

/**
 *
 * @author farooq
 */
public class Student  extends Person {
    
    String rollNo;
    
    ArrayList<Course> courses;
    
    public Student(String r, String n, Address a){
        super(n,a);
        rollNo = r;
        courses = new ArrayList<Course>();
    }
    
    public void print(){
        System.out.println("RollNo: " + rollNo);
        super.print();
    }
    
    public int compare(Comparable p) throws NotComparableException{
        if (p instanceof Student){
            Student s = (Student) p;
            
            return  this.rollNo.compareToIgnoreCase(s.rollNo);
        }
        
        throw new NotComparableException();
    }
    
    public void printCourses(){
        for(Course c : courses){
            c.print();
        }
    }
    
    public void addCourse(Course c){
        if (!courses.contains(c)){
          courses.add(c);
          c.addStudent(this);
        }
    }
    
    public ArrayList<Course> getCourses(){
        return courses;
    }
}
