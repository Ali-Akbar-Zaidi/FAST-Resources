package Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Controller.authentication.Employee;
import Controller.schemas.Employee_Data;

public class Employee_Test {

    private Employee employee;
    private Employee_Data employeeData;

    @Before
    public void setUp() {
        employee = new Employee();
        employeeData = new Employee_Data("test_user", "password123");
    }

    @Test
    public void testAddEmployee_UsernameExists() {
        employee.employee_data.clear();
        employee.employee_data.add(employeeData);

        boolean result = employee.add_employee("test_user", "new_password");
        assertFalse(result);
    }

    @Test
    public void testChangePassword_Success() {
        employee.employee_data.clear();
        employee.employee_data.add(employeeData);

        boolean result = employee.change_password("test_user", "password123", "newpassword123");
        assertTrue(result);
    }

    @Test
    public void testChangePassword_UsernameNotFound() {
        employee.employee_data.clear();
        boolean result = employee.change_password("unknown_user", "password123", "newpassword123");
        assertFalse(result);
    }

    @Test
    public void testChangePassword_InvalidOldPassword() {
        employee.employee_data.clear();
        employee.employee_data.add(employeeData);

        boolean result = employee.change_password("test_user", "wrongpassword", "newpassword123");
        assertFalse(result);
    }

    @Test
    public void testAuthenticate_Success() {
        employee.employee_data.clear();
        employee.employee_data.add(employeeData);

        boolean result = employee.authenticate("test_user", "password123");
        assertTrue(result);
    }

    @Test
    public void testAuthenticate_InvalidPassword() {
        employee.employee_data.clear();
        employee.employee_data.add(employeeData);

        boolean result = employee.authenticate("test_user", "wrongpassword");
        assertFalse(result);
    }

    @Test
    public void testAuthenticate_UsernameNotFound() {
        employee.employee_data.clear();
        boolean result = employee.authenticate("unknown_user", "password123");
        assertFalse(result);
    }

    @Test
    public void testEmployeeExists_True() {
        employee.employee_data.clear();
        employee.employee_data.add(employeeData);

        boolean result = employee.employee_exists("test_user");
        assertTrue(result);
    }

    @Test
    public void testEmployeeExists_False() {
        employee.employee_data.clear();
        boolean result = employee.employee_exists("unknown_user");
        assertFalse(result);
    }

    @Test
    public void testSaveData_Success() {
        employee.employee_data.clear();
        employee.employee_data.add(employeeData);
        employee.save_data();
        employee.load_data();
        int size = employee.employee_data.size();
        assertEquals(1, size);
        employee.employee_data.clear();
        employee.save_data();
        employee.load_data();
    }
}
