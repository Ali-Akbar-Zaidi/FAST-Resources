/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package academics;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author amyaw
 */
public class StudentTest {
    
    public StudentTest() {
    }
    
    @Test
    public void testCompare1(){
        try {
            Student s1 = new Student("1001","Ahmed",new Address("Faisal Town","Lahore","Pakistan"));
            assertEquals(0, s1.compare(s1));
            
            
        } catch (NotComparableException ex) {
            Logger.getLogger(StudentTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testCompare2(){
        try {
            Student s1 = new Student("1001","Ahmed",new Address("Faisal Town","Lahore","Pakistan"));
            Student s2 = new Student("1002","Ali",new Address("Faisal Town","Lahore","Pakistan"));
            
            assertEquals(-1, s1.compare(s2));
            
            
        } catch (NotComparableException ex) {
            Logger.getLogger(StudentTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testCompare3(){
        try {
            Student s1 = new Student("1001","Ahmed",new Address("Faisal Town","Lahore","Pakistan"));
            Student s2 = new Student("1002","Ali",new Address("Faisal Town","Lahore","Pakistan"));
            
            assertEquals(1, s2.compare(s1));
            
            
        } catch (NotComparableException ex) {
            Logger.getLogger(StudentTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
