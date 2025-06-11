package Tests;

import org.junit.Before;
import org.junit.Test;
import java.sql.DriverManager;

import Connection.Connect;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Connect_Test {

    private Connect connect;

    @Before
    public void setUp() {
        connect = new Connect();
    }

    @Test
    public void testGetConnection() {
        Connection conn = Connect.getConnection();

        try {
            // Assert that the connection is valid
            assertNotNull("Connection should not be null", conn);
            assertTrue("Connection should be valid", conn.isValid(2));
        } catch (SQLException e) {
            fail("SQLException occurred while validating the connection: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidConnection() {
        String invalidUrl = "jdbc:mysql://localhost:3306/invalid_db";
        String invalidUsername = "wrong_user";
        String invalidPassword = "wrong_password";

        try {
            Connection conn = DriverManager.getConnection(invalidUrl, invalidUsername, invalidPassword);
            fail("Connection should not be established with invalid credentials.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
