package Tests.Schemas;

import org.junit.Before;
import org.junit.Test;

import Controller.schemas.NADRA_Data;

import static org.junit.Assert.*;

public class NADRA_Data_Test {

    private NADRA_Data nadraData;

    @Before
    public void setUp() {

        nadraData = new NADRA_Data();
    }

    @Test
    public void testDefaultConstructor() {

        assertEquals("", nadraData.CNIC);
        assertEquals("", nadraData.issue_date);
        assertEquals("", nadraData.expiry_date);
    }

    @Test
    public void testParameterizedConstructor() {

        NADRA_Data data = new NADRA_Data("1234567890123", "01/01/2000", "01/01/2030");

        assertEquals("1234567890123", data.CNIC);
        assertEquals("01/01/2000", data.issue_date);
        assertEquals("01/01/2030", data.expiry_date);
    }

    @Test
    public void testGetString() {

        NADRA_Data data = new NADRA_Data("1234567890123", "01/01/2000", "01/01/2030");

        String expectedString = "1234567890123,01/01/2000,01/01/2030";
        assertEquals(expectedString, data.getString());
    }

    @Test
    public void testSettersAndGetters() {

        nadraData.CNIC = "9876543210987";
        nadraData.issue_date = "15/08/2005";
        nadraData.expiry_date = "15/08/2025";

        assertEquals("9876543210987", nadraData.CNIC);
        assertEquals("15/08/2005", nadraData.issue_date);
        assertEquals("15/08/2025", nadraData.expiry_date);
    }
}
