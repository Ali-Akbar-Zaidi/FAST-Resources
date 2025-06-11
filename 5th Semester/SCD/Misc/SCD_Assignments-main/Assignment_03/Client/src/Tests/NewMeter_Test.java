package Tests;

import org.junit.Test;

import View.NewMeter;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewMeter_Test {

    @Test
    public void testIsValidDateFormat() {
        // Valid date
        assertTrue("Valid date format should return true", NewMeter.is_valid_date_format("15/12/2024"));

        // Invalid date format
        assertFalse("Invalid date format should return false", NewMeter.is_valid_date_format("2024-12-15"));

        // Invalid date (nonexistent day)
        assertFalse("Invalid date (31/02/2024) should return false", NewMeter.is_valid_date_format("31/02/2024"));

        // Empty string
        assertFalse("Empty string should return false", NewMeter.is_valid_date_format(""));

        // Null input
        assertFalse("Null input should return false", NewMeter.is_valid_date_format(null));
    }

    @Test
    public void testDateIsBeforeToday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();

        // Future date
        String futureDate = today.plusDays(1).format(formatter);
        assertTrue("Future date should return true", NewMeter.date_is_before_today(futureDate));

        // Current date
        String currentDate = today.format(formatter);
        assertTrue("Current date should return true", NewMeter.date_is_before_today(currentDate));

        // Past date
        String pastDate = today.minusDays(1).format(formatter);
        assertFalse("Past date should return false", NewMeter.date_is_before_today(pastDate));

        // Invalid date format
        assertFalse("Invalid date format should return false", NewMeter.date_is_before_today("2024-12-15"));

        // Null input
        assertFalse("Null input should return false", NewMeter.date_is_before_today(null));
    }

    @Test
    public void testRandomNum() {
        // Generate multiple random numbers and ensure they are within range
        for (int i = 0; i < 100; i++) {
            int randomNumber = Integer.parseInt(NewMeter.randomNum());
            assertTrue("Random number should be within range 0-9999", randomNumber >= 0 && randomNumber < 10000);
        }
    }
}
