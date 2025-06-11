package Tests.Schemas;

import org.junit.Before;
import org.junit.Test;

import Controller.schemas.Billing_Data;

import static org.junit.Assert.*;

public class Billing_Data_Test {

    private Billing_Data billingData;

    @Before
    public void setUp() {

        billingData = new Billing_Data();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals("", billingData.cust_id);
        assertEquals("", billingData.billing_month);
        assertEquals("", billingData.billing_year);
        assertEquals("", billingData.curr_meter_reading);
        assertEquals("", billingData.curr_meter_reading_peak);
        assertEquals("", billingData.reading_entry_date);
        assertEquals("", billingData.electricity_cost);
        assertEquals("", billingData.sales_tax);
        assertEquals("", billingData.fixed_charges);
        assertEquals("", billingData.total_billing_amount);
        assertEquals("", billingData.due_date);
        assertEquals("", billingData.bill_status);
        assertEquals("", billingData.bill_payment_date);
    }

    @Test
    public void testParameterizedConstructor() {

        Billing_Data data = new Billing_Data("12345", "January", "2024", "100", "120", "01/01/2024", "200.50", "15.50",
                "50.00", "266.00", "08/01/2024", "Paid", "05/01/2024");

        assertEquals("12345", data.cust_id);
        assertEquals("January", data.billing_month);
        assertEquals("2024", data.billing_year);
        assertEquals("100", data.curr_meter_reading);
        assertEquals("120", data.curr_meter_reading_peak);
        assertEquals("01/01/2024", data.reading_entry_date);
        assertEquals("200.50", data.electricity_cost);
        assertEquals("15.50", data.sales_tax);
        assertEquals("50.00", data.fixed_charges);
        assertEquals("266.00", data.total_billing_amount);
        assertEquals("08/01/2024", data.due_date);
        assertEquals("Paid", data.bill_status);
        assertEquals("05/01/2024", data.bill_payment_date);
    }

    @Test
    public void testGetString() {

        Billing_Data data = new Billing_Data("12345", "January", "2024", "100", "120", "01/01/2024", "200.50", "15.50",
                "50.00", "266.00", "08/01/2024", "Paid", "05/01/2024");

        String expectedString = "12345,January,2024,100,120,01/01/2024,200.50,15.50,50.00,266.00,08/01/2024,05/01/2024,Paid";
        assertEquals(expectedString, data.getString());
    }

    @Test
    public void testSettersAndGetters() {

        billingData.cust_id = "67890";
        billingData.billing_month = "February";
        billingData.billing_year = "2024";
        billingData.curr_meter_reading = "150";
        billingData.curr_meter_reading_peak = "180";
        billingData.reading_entry_date = "02/02/2024";
        billingData.electricity_cost = "300.75";
        billingData.sales_tax = "20.25";
        billingData.fixed_charges = "60.00";
        billingData.total_billing_amount = "381.00";
        billingData.due_date = "09/02/2024";
        billingData.bill_status = "Unpaid";
        billingData.bill_payment_date = "07/02/2024";

        assertEquals("67890", billingData.cust_id);
        assertEquals("February", billingData.billing_month);
        assertEquals("2024", billingData.billing_year);
        assertEquals("150", billingData.curr_meter_reading);
        assertEquals("180", billingData.curr_meter_reading_peak);
        assertEquals("02/02/2024", billingData.reading_entry_date);
        assertEquals("300.75", billingData.electricity_cost);
        assertEquals("20.25", billingData.sales_tax);
        assertEquals("60.00", billingData.fixed_charges);
        assertEquals("381.00", billingData.total_billing_amount);
        assertEquals("09/02/2024", billingData.due_date);
        assertEquals("Unpaid", billingData.bill_status);
        assertEquals("07/02/2024", billingData.bill_payment_date);
    }
}
