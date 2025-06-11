package Tests;

import org.junit.Test;

import View.UpdateCNIC;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateCNIC_Test {

    @Test
    public void testIsValidDateFormat() {
        // Valid date format
        assertTrue("Valid date should return true", UpdateCNIC.is_valid_date_format("12/12/2024"));

        // Invalid date format (wrong day/month format)
        assertFalse("Invalid date should return false", UpdateCNIC.is_valid_date_format("2024/12/12"));

        // Invalid date format (non-existent date)
        assertFalse("Invalid date should return false", UpdateCNIC.is_valid_date_format("31/02/2024"));

        // Invalid date format (empty string)
        assertFalse("Empty date should return false", UpdateCNIC.is_valid_date_format(""));
    }

    @Test
    public void testDateIsBeforeToday() {
        // Date is before today
        String pastDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        assertFalse("Date before today should return false", UpdateCNIC.date_is_before_today(pastDate));

        // Date is today's date
        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        assertTrue("Today's date should return true", UpdateCNIC.date_is_before_today(todayDate));

        // Date is after today
        String futureDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        assertTrue("Future date should return true", UpdateCNIC.date_is_before_today(futureDate));

    }

    @Test
    public void testFirstIsAfterSecond() {
        // First date is after second date
        assertTrue("First date should be after second date",
                UpdateCNIC.first_is_after_second("01/01/2025", "01/01/2024"));

        // First date is same as second date
        assertFalse("First date and second date should be equal",
                UpdateCNIC.first_is_after_second("01/01/2024", "01/01/2024"));

        // First date is before second date
        assertFalse("First date should not be before second date",
                UpdateCNIC.first_is_after_second("01/01/2023", "01/01/2024"));
    }

    @Test
    public void testGetTodaysDateInFormat() {
        // Today's date should match the current date in the expected format
        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        assertEquals("Today's date should match the expected format", todayDate,
                UpdateCNIC.get_todays_date_in_format());
    }
}
