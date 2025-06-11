package Tests;

import static org.junit.Assert.*;

import View.CreateMonthsBill;
import Controller.schemas.Customer_Data;
import Controller.schemas.Tariff_Data;
import org.junit.Test;

public class CreateMonthsBill_Test {

    // Mock Client class to simulate network requests
    @Test
    public void testCustomerData() {
        // Create a sample customer
        Customer_Data customer = new Customer_Data("1234", "1234567890123", "John Doe", "123 Main St", "03001234567",
                "Domestic", "Single Phase", "100", "200");

        // Test that customer details are correctly initialized
        assertEquals("1234", customer.Unique_ID);
        assertEquals("1234567890123", customer.CNIC);
        assertEquals("John Doe", customer.Name);
        assertEquals("123 Main St", customer.Address);
        assertEquals("03001234567", customer.Phone_No);
        assertEquals("Domestic", customer.cust_type);
        assertEquals("Single Phase", customer.meter_type);
        assertEquals("200", customer.reg_units_comsumed);
        assertEquals("100", customer.peak_units_comsumed);
    }

    @Test
    public void testTariffData() {
        // Create sample tariff data
        Tariff_Data tariff = new Tariff_Data("Single Phase", "10", "", "15", "100");

        // Test that tariff data is correctly initialized
        assertEquals("Single Phase", tariff.Meter_Type);
        assertEquals("10", tariff.reg_unit_price);
        assertEquals("", tariff.peak_unit_price); // For Single Phase, peak_unit_price should be empty
        assertEquals("15", tariff.percent_of_Tax);
        assertEquals("100", tariff.fixed_charges);
    }

    @Test
    public void testRandomNum() {
        // Test that randomNum generates a number within the expected range
        String randomNum = CreateMonthsBill.randomNum();
        assertTrue(Integer.parseInt(randomNum) >= 0 && Integer.parseInt(randomNum) < 10000);
    }

    @Test
    public void testWeekLater() {
        // Test that weekLater returns the correct date (7 days later)
        String weekLater = CreateMonthsBill.weekLater();
        assertNotNull(weekLater);
        assertEquals(java.time.LocalDate.now().plusDays(7)
                .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")), weekLater);
    }
}
