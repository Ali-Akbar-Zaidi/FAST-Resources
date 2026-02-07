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
public class Course {
    String code;
    
    ArrayList<Student> students;
    ArrayList<Course> preReqs;  
    ArrayList<Course> successors;
    ArrayList<Course> exclusiveCourses;
    
    
    public Course(String c){
        code = c;
        students = new ArrayList<Student>();
    }
    
    public void print(){
        System.out.println("Code:" + code);
    }
    
    public void printStudents(){
        System.out.println("Number of students: " + students.size());
        for (Student s : students){
            //s.print();
        }
    }
    
    public void addStudent(Student s){
        if (!students.contains(s)){
       
            students.add(s);
            s.addCourse(this);
        }
    }
    
    
}
