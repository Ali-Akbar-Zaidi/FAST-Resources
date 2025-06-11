package Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Controller.billing.Billing;
import Controller.schemas.Billing_Data;
import java.util.ArrayList;

public class Billing_Test {

    private Billing billing;
    private Billing_Data billingData;

    @Before
    public void setUp() {
        billing = new Billing();
        billingData = new Billing_Data("C001", "January", "2024", "100", "200", "01/01/2024", "500", "50", "100", "650",
                "15/01/2024", "Paid", "05/01/2024");
    }

    @Test
    public void testAdd_Success() {
        billing.billing_data.clear();
        boolean result = billing.add(billingData);
        assertTrue(result);
    }

    @Test
    public void testGetReportsPaid_Success() {
        billing.billing_data.clear();
        billing.billing_data.add(billingData);
        billing.billing_data.add(new Billing_Data("C002", "February", "2024", "150", "250", "01/02/2024", "600", "60",
                "120", "780", "15/02/2024", "Unpaid", "10/02/2024"));

        int paidReports = billing.get_reports_paid();
        assertEquals(1, paidReports);
    }

    @Test
    public void testGetReportsUnpaid_Success() {
        billing.billing_data.clear();
        billing.billing_data.add(billingData);
        billing.billing_data.add(new Billing_Data("C002", "February", "2024", "150", "250", "01/02/2024", "600", "60",
                "120", "780", "15/02/2024", "Unpaid", "10/02/2024"));

        int unpaidReports = billing.get_reports_unpaid();
        assertEquals(1, unpaidReports);
    }

    @Test
    public void testGetLatestDate_Success() {
        billing.billing_data.clear();
        billing.billing_data.add(billingData);
        billing.billing_data.add(new Billing_Data("C002", "February", "2024", "150", "250", "01/02/2024", "600", "60",
                "120", "780", "15/02/2024", "Paid", "10/02/2024"));

        String latestDate = billing.get_latest_date();
        assertEquals("01/02/2024", latestDate);
    }

    @Test
    public void testGetMaxDate_Success() {
        billing.billing_data.clear();
        billing.billing_data.add(billingData);
        billing.billing_data.add(new Billing_Data("C002", "February", "2024", "150", "250", "01/02/2024", "600", "60",
                "120", "780", "15/02/2024", "Paid", "10/02/2024"));

        String maxDate = billing.getMaxDate();
        assertEquals("01/02/2024", maxDate);
    }

    @Test
    public void testUpdateEditBillingData_Success() {
        billing.billing_data.clear();
        billing.billing_data.add(billingData);

        Billing_Data updatedData = new Billing_Data("C001", "January", "2024", "120", "220", "01/01/2024", "550", "55",
                "110", "715", "15/01/2024", "Paid", "05/01/2024");
        boolean result = billing.update_edit_billing_data(updatedData);
        assertTrue(result);
    }

    @Test
    public void testUpdateEditBillingData_Failure() {
        billing.billing_data.clear();
        billing.billing_data.add(billingData);

        Billing_Data updatedData = new Billing_Data("C003", "March", "2024", "120", "220", "01/03/2024", "550", "55",
                "110", "715", "15/03/2024", "Paid", "05/03/2024");
        boolean result = billing.update_edit_billing_data(updatedData);
        assertFalse(result);
    }

    @Test
    public void testDeleteBill_Success() {
        billing.billing_data.clear();
        billing.billing_data.add(billingData);

        boolean result = billing.delete_bill("C001", "January", "2024", "01/01/2024");
        assertTrue(result);
    }

    @Test
    public void testDeleteBill_Failure() {
        billing.billing_data.clear();
        billing.billing_data.add(billingData);

        boolean result = billing.delete_bill("C003", "March", "2024", "01/03/2024");
        assertFalse(result);
    }

    @Test
    public void testGetBill_Success() {
        billing.billing_data.clear();
        billing.billing_data.add(billingData);

        ArrayList<Billing_Data> bills = billing.get_bill("C001");
        assertEquals(1, bills.size());
    }

    @Test
    public void testGetBill_Empty() {
        billing.billing_data.clear();
        ArrayList<Billing_Data> bills = billing.get_bill("C003");
        assertTrue(bills.isEmpty());
    }
}
