package Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Controller.authentication.Customer;
import Controller.schemas.Customer_Data;

import java.sql.*;
import java.util.List;

public class Customer_Test {

        private Customer customer;

        @Before
        public void setUp() {
                customer = new Customer();
                customer.customer_data.clear();
                customer.save_data();
                customer.load_data();
        }

        @Test
        public void testAddMeter() {
                customer.customer_data.clear();
                boolean result = customer.add_meter("5678", "1234567890123", "Jane Doe", "456 Elm St", "0987654321",
                                "Commercial", "Three Phase", "200", "250");
                assertTrue("Meter should be added successfully", result);
                assertEquals("Customer data size should increase by 1", 1, customer.customer_size());
        }

        @Test
        public void testCountMeters() {
                customer.customer_data.clear();
                customer.add_meter("5678", "1234567890123", "Jane Doe", "456 Elm St", "0987654321", "Commercial",
                                "Three Phase",
                                "200", "250");
                int count = customer.count_meters("1234567890123");
                assertEquals("There should be 1 meter for this CNIC", 1, count);
        }

        @Test
        public void testIsUnique() {
                customer.customer_data.clear();
                customer.add_meter("5678", "1234567890123", "Jane Doe", "456 Elm St", "0987654321", "Commercial",
                                "Three Phase",
                                "200", "250");
                assertFalse("Unique ID should not be unique", customer.isUnique("5678"));
                customer.customer_data.clear();
                assertTrue("Unique ID should be unique after adding", customer.isUnique("5678"));
        }

        @Test
        public void testMatchWithMeterType() {
                customer.customer_data.clear();
                customer.add_meter("5678", "1234567890123", "Jane Doe", "456 Elm St", "0987654321", "Commercial",
                                "Three Phase",
                                "200", "250");
                boolean result = customer.match("5678", "1234567890123", "Three Phase");
                assertTrue("Customer should match", result);
        }

        @Test
        public void testMatchWithoutMeterType() {
                customer.customer_data.clear();
                customer.add_meter("5678", "1234567890123", "Jane Doe", "456 Elm St", "0987654321", "Commercial",
                                "Three Phase",
                                "200", "250");
                boolean result = customer.match("5678", "1234567890123");
                assertTrue("Customer should match", result);
        }

        @Test
        public void testGetCustomer() {
                customer.customer_data.clear();
                customer.add_meter("5678", "1234567890123", "Jane Doe", "456 Elm St", "0987654321", "Commercial",
                                "Three Phase",
                                "200", "250");
                Customer_Data result = customer.getCustomer("1234567890123");
                assertNotNull("Customer should not be null", result);
                assertEquals("Customer name should be Jane Doe", "Jane Doe", result.Name);
        }

        @Test
        public void testDeleteFromUniqueId() {
                customer.customer_data.clear();
                customer.add_meter("5678", "1234567890123", "Jane Doe", "456 Elm St", "0987654321", "Commercial",
                                "Three Phase",
                                "200", "250");
                boolean result = customer.delete_from_unique_id("5678");
                assertTrue("Customer should be deleted successfully", result);
                assertEquals("Customer data size should decrease by 1", 0, customer.customer_size());
        }

        @Test
        public void testReplaceCustomerDataByUniqueId() {
                customer.customer_data.clear();
                customer.add_meter("5678", "1234567890123", "Jane Doe", "456 Elm St", "0987654321", "Commercial",
                                "Three Phase",
                                "200", "250");
                Customer_Data updatedData = new Customer_Data("5678", "1234567890123", "Jane Doe Updated", "456 Elm St",
                                "0987654321", "Commercial", "Single Phase", "300", "400");
                boolean result = customer.replace_customer_data_by_unique_id(updatedData);
                assertTrue("Customer data should be replaced successfully", result);
                assertEquals("Updated customer name should be Jane Doe Updated", "Jane Doe Updated",
                                customer.getCustomer("1234567890123").Name);
        }

        @Test
        public void testAddToUnits() {
                customer.customer_data.clear();
                customer.add_meter("5678", "1234567890123", "Jane Doe", "456 Elm St", "0987654321", "Commercial",
                                "Three Phase",
                                "200", "250");
                boolean result = customer.add_to_units("5678", "50", "100");
                assertTrue("Units should be added successfully", result);
                Customer_Data updatedCustomer = customer.getCustomer("1234567890123");
                assertEquals("Updated regular units should be 300", "300", updatedCustomer.reg_units_comsumed);
                assertEquals("Updated peak units should be 300", "300", updatedCustomer.peak_units_comsumed);
        }

        @Test
        public void testCustIdExists() {
                customer.customer_data.clear();
                customer.add_meter("5678", "1234567890123", "Jane Doe", "456 Elm St", "0987654321", "Commercial",
                                "Three Phase",
                                "200", "250");
                assertTrue("Customer ID should exist", customer.cust_id_exsts("5678"));
                assertFalse("Customer ID should not exist", customer.cust_id_exsts("9999"));
        }

        @Test
        public void testSaveData() {
                customer.customer_data.clear();
                customer.add_meter("5678", "1234567890123", "Jane Doe", "456 Elm St",
                                "0987654321", "Commercial",
                                "Three Phase",
                                "200", "250");
                customer.save_data();

                customer.load_data();
                int size = customer.customer_size();
                assertEquals("Customer data size should be 1", 1, size);
                customer.customer_data.clear();
                customer.save_data();
                customer.load_data();
        }

}
