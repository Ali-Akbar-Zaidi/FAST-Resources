package Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Controller.schemas.Tariff_Data;

public class Tariff_Data_Test {

    private Tariff_Data tariffData;

    @Before
    public void setUp() {
        tariffData = new Tariff_Data("Single Phase", "5.00", "7.00", "10", "100");
    }

    @Test
    public void testConstructor_Success() {
        assertEquals("Single Phase", tariffData.Meter_Type);
        assertEquals("5.00", tariffData.reg_unit_price);
        assertEquals("7.00", tariffData.peak_unit_price);
        assertEquals("10", tariffData.percent_of_Tax);
        assertEquals("100", tariffData.fixed_charges);
    }

    @Test
    public void testDefaultConstructor_Success() {
        Tariff_Data defaultTariff = new Tariff_Data();
        assertEquals("", defaultTariff.Meter_Type);
        assertEquals("", defaultTariff.reg_unit_price);
        assertEquals("", defaultTariff.peak_unit_price);
        assertEquals("", defaultTariff.percent_of_Tax);
        assertEquals("", defaultTariff.fixed_charges);
    }

    @Test
    public void testGetString_Success() {
        String expectedString = "Single Phase,5.00,7.00,10,100";
        String result = tariffData.getString();
        assertEquals(expectedString, result);
    }

    @Test
    public void testSettersAndGetters_Success() {
        tariffData.Meter_Type = "Three Phase";
        tariffData.reg_unit_price = "6.00";
        tariffData.peak_unit_price = "8.00";
        tariffData.percent_of_Tax = "12";
        tariffData.fixed_charges = "150";

        assertEquals("Three Phase", tariffData.Meter_Type);
        assertEquals("6.00", tariffData.reg_unit_price);
        assertEquals("8.00", tariffData.peak_unit_price);
        assertEquals("12", tariffData.percent_of_Tax);
        assertEquals("150", tariffData.fixed_charges);
    }
}
