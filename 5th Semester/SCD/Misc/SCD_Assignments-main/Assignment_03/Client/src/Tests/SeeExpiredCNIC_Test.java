package Tests;

import org.junit.Test;

import View.SeeExpiredCNIC;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeeExpiredCNIC_Test {

    @Test
    public void testCheckCNICExpiry() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();

        // Case 1: CNIC is expired
        String expiredDate = today.minusDays(1).format(formatter);
        assertEquals("Expired CNIC should return -1", -1, SeeExpiredCNIC.checkCNICExpiry(expiredDate));

        // Case 2: CNIC is within 30 days of expiry
        String nearExpiryDate = today.plusDays(15).format(formatter);
        assertEquals("CNIC near expiry should return 1", 1, SeeExpiredCNIC.checkCNICExpiry(nearExpiryDate));

        // Case 3: CNIC is not close to expiry
        String farExpiryDate = today.plusDays(60).format(formatter);
        assertEquals("CNIC not close to expiry should return 0", 0, SeeExpiredCNIC.checkCNICExpiry(farExpiryDate));

        // Case 4: CNIC expires exactly today
        String expiresToday = today.format(formatter);
        assertEquals("CNIC expiring today should return 1", 1, SeeExpiredCNIC.checkCNICExpiry(expiresToday));

        // Case 5: Invalid format (should throw an exception)
        try {
            SeeExpiredCNIC.checkCNICExpiry("2024-12-01");
            fail("Invalid date format should throw an exception");
        } catch (Exception e) {
            assertTrue("Exception message should mention invalid format",
                    e.getMessage().contains("Text '2024-12-01' could not be parsed"));
        }
    }
}
