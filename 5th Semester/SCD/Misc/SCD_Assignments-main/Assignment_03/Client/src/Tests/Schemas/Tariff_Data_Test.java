package Tests.Schemas;

import org.junit.Before;
import org.junit.Test;

import Controller.schemas.Tariff_Data;

import static org.junit.Assert.*;

public class Tariff_Data_Test {

    private Tariff_Data tariffData;

    @Before
    public void setUp() {
        tariffData = new Tariff_Data();
    }

    @Test
    public void testDefaultConstructor() {

        assertEquals("", tariffData.Meter_Type);
        assertEquals("", tariffData.reg_unit_price);
        assertEquals("", tariffData.peak_unit_price);
        assertEquals("", tariffData.percent_of_Tax);
        assertEquals("", tariffData.fixed_charges);
    }

    @Test
    public void testParameterizedConstructor() {
        Tariff_Data data = new Tariff_Data("Single Phase", "5.5", "8.0", "15", "50");

        assertEquals("Single Phase", data.Meter_Type);
        assertEquals("5.5", data.reg_unit_price);
        assertEquals("8.0", data.peak_unit_price);
        assertEquals("15", data.percent_of_Tax);
        assertEquals("50", data.fixed_charges);
    }

    @Test
    public void testGetString() {
        Tariff_Data data = new Tariff_Data("Three Phase", "6.5", "9.0", "18", "60");

        String expectedString = "Three Phase,6.5,9.0,18,60";
        assertEquals(expectedString, data.getString());
    }

    @Test
    public void testSettersAndGetters() {
        tariffData.Meter_Type = "Single Phase";
        tariffData.reg_unit_price = "5.5";
        tariffData.peak_unit_price = "7.5";
        tariffData.percent_of_Tax = "12";
        tariffData.fixed_charges = "40";

        assertEquals("Single Phase", tariffData.Meter_Type);
        assertEquals("5.5", tariffData.reg_unit_price);
        assertEquals("7.5", tariffData.peak_unit_price);
        assertEquals("12", tariffData.percent_of_Tax);
        assertEquals("40", tariffData.fixed_charges);
    }
}
