package Tests.Schemas;

import org.junit.Before;
import org.junit.Test;

import Controller.schemas.Employee_Data;

import static org.junit.Assert.*;

public class Employee_Data_Test {

    private Employee_Data employeeData;

    @Before
    public void setUp() {

        employeeData = new Employee_Data();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals("", employeeData.username);
        assertEquals("", employeeData.password);
    }

    @Test
    public void testParameterizedConstructor() {

        Employee_Data data = new Employee_Data("john_doe", "password123");

        assertEquals("john_doe", data.username);
        assertEquals("password123", data.password);
    }

    @Test
    public void testGetString() {

        Employee_Data data = new Employee_Data("john_doe", "password123");

        String expectedString = "john_doe,password123";
        assertEquals(expectedString, data.getString());
    }

    @Test
    public void testSettersAndGetters() {
        employeeData.username = "jane_doe";
        employeeData.password = "securePass123";

        assertEquals("jane_doe", employeeData.username);
        assertEquals("securePass123", employeeData.password);
    }
}
