package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import View.EditBillingFile.ButtonEditor;

public class EditBillingFile_Test {
    @Test
    public void testIsValidDateFormatValid() {
        // Test valid date formats
        assertTrue("01/12/2023 should be valid", ButtonEditor.is_valid_date_format("01/12/2023"));
        assertTrue("29/02/2024 should be valid (leap year)", ButtonEditor.is_valid_date_format("29/02/2024"));
    }

    @Test
    public void testIsValidDateFormatInvalid() {
        // Test invalid date formats
        assertFalse("31/02/2023 should be invalid", ButtonEditor.is_valid_date_format("31/02/2023"));
        assertFalse("Invalid date format should return false", ButtonEditor.is_valid_date_format("12-01-2023"));
        assertFalse("Empty string should return false", ButtonEditor.is_valid_date_format(""));
    }

    @Test
    public void testFirstIsAfterSecond() {
        // Test dates where the first is after the second
        assertTrue("02/01/2023 is after 01/01/2023", ButtonEditor.first_is_after_second("02/01/2023", "01/01/2023"));
        assertTrue("01/03/2023 is after 01/02/2023", ButtonEditor.first_is_after_second("01/03/2023", "01/02/2023"));
        assertTrue("01/01/2024 is after 01/01/2023", ButtonEditor.first_is_after_second("01/01/2024", "01/01/2023"));

        // Test dates where the first is not after the second
        assertFalse("01/01/2023 is not after 02/01/2023",
                ButtonEditor.first_is_after_second("01/01/2023", "02/01/2023"));
        assertFalse("01/02/2023 is not after 01/03/2023",
                ButtonEditor.first_is_after_second("01/02/2023", "01/03/2023"));
        assertFalse("01/01/2023 is not after 01/01/2023",
                ButtonEditor.first_is_after_second("01/01/2023", "01/01/2023"));
    }

    @Test
    public void testFirstIsEqualToOrAfterSecond() {
        // Test dates where the first is equal to or after the second
        assertTrue("01/01/2023 is equal to 01/01/2023",
                ButtonEditor.first_is_equal_to_or_after_second("01/01/2023", "01/01/2023"));
        assertTrue("02/01/2023 is after 01/01/2023",
                ButtonEditor.first_is_equal_to_or_after_second("02/01/2023", "01/01/2023"));
        assertTrue("01/02/2023 is after 01/01/2023",
                ButtonEditor.first_is_equal_to_or_after_second("01/02/2023", "01/01/2023"));

        // Test dates where the first is not equal to or after the second
        assertFalse("01/01/2023 is not after 02/01/2023",
                ButtonEditor.first_is_equal_to_or_after_second("01/01/2023", "02/01/2023"));
        assertFalse("01/01/2023 is not after 01/02/2023",
                ButtonEditor.first_is_equal_to_or_after_second("01/01/2023", "01/02/2023"));
    }

}
