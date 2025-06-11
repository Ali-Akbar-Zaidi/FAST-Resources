package Tests.Schemas;

import org.junit.Before;
import org.junit.Test;

import Controller.schemas.Customer_Data;

import static org.junit.Assert.*;

public class Customer_Data_Test {

    private Customer_Data customerData;

    @Before
    public void setUp() {

        customerData = new Customer_Data();
    }

    @Test
    public void testDefaultConstructor() {

        assertEquals("", customerData.Unique_ID);
        assertEquals("", customerData.CNIC);
        assertEquals("", customerData.Name);
        assertEquals("", customerData.Address);
        assertEquals("", customerData.Phone_No);
        assertEquals("", customerData.cust_type);
        assertEquals("", customerData.meter_type);
        assertEquals("", customerData.reg_units_comsumed);
        assertEquals("", customerData.peak_units_comsumed);
    }

    @Test
    public void testParameterizedConstructor() {
        Customer_Data data = new Customer_Data("1234", "1234567890123", "John Doe", "123 Main St", "03011234567",
                "Domestic", "Single Phase", "100", "200");

        assertEquals("1234", data.Unique_ID);
        assertEquals("1234567890123", data.CNIC);
        assertEquals("John Doe", data.Name);
        assertEquals("123 Main St", data.Address);
        assertEquals("03011234567", data.Phone_No);
        assertEquals("Domestic", data.cust_type);
        assertEquals("Single Phase", data.meter_type);
        assertEquals("200", data.reg_units_comsumed);
        assertEquals("100", data.peak_units_comsumed);
    }

    @Test
    public void testGetString() {
        Customer_Data data = new Customer_Data("1234", "1234567890123", "John Doe", "123 Main St", "03011234567",
                "Domestic", "Single Phase", "100", "200");

        String expectedString = "1234,1234567890123,John Doe,123 Main St,03011234567,Domestic,Single Phase,100,200";
        assertEquals(expectedString, data.getString());
    }

    @Test
    public void testSettersAndGetters() {

        customerData.Unique_ID = "5678";
        customerData.CNIC = "9876543210987";
        customerData.Name = "Jane Doe";
        customerData.Address = "456 Elm St";
        customerData.Phone_No = "03123456789";
        customerData.cust_type = "Commercial";
        customerData.meter_type = "Three Phase";
        customerData.reg_units_comsumed = "150";
        customerData.peak_units_comsumed = "250";

        assertEquals("5678", customerData.Unique_ID);
        assertEquals("9876543210987", customerData.CNIC);
        assertEquals("Jane Doe", customerData.Name);
        assertEquals("456 Elm St", customerData.Address);
        assertEquals("03123456789", customerData.Phone_No);
        assertEquals("Commercial", customerData.cust_type);
        assertEquals("Three Phase", customerData.meter_type);
        assertEquals("150", customerData.reg_units_comsumed);
        assertEquals("250", customerData.peak_units_comsumed);
    }
}
