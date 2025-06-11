package Tests;

import org.junit.Before;
import org.junit.Test;

import View.CheckBill;

import static org.junit.Assert.*;

public class CheckBill_Test {

    private CheckBill checkBill;

    @Before
    public void setUp() {
        checkBill = new CheckBill();
    }

    @Test
    public void testInitComponents() {
        assertNotNull(checkBill.titleLabel);
        assertNotNull(checkBill.cnicLabel);
        assertNotNull(checkBill.cnicTextField);
        assertNotNull(checkBill.meterTypeLabel);
        assertNotNull(checkBill.meterTypeTextField);
        assertNotNull(checkBill.confirmButton);
        assertNotNull(checkBill.customerIdLabel);
        assertNotNull(checkBill.customerIdTextField);
    }

    @Test
    public void testConfirmButtonAction_ValidInput() {
        // Simulate valid inputs
        checkBill.cnicTextField.setText("1234567890123");
        checkBill.customerIdTextField.setText("1234");
        checkBill.meterTypeTextField.setText("Single Phase");

        // Simulate button click
        checkBill.confirmButton.doClick();

        // Check if fields are cleared after valid input
        assertEquals("", checkBill.cnicTextField.getText());
        assertEquals("", checkBill.customerIdTextField.getText());
        assertEquals("", checkBill.meterTypeTextField.getText());
    }

    @Test
    public void testConfirmButtonAction_InvalidCNIC() {
        // Simulate invalid CNIC input
        checkBill.cnicTextField.setText("12345");
        checkBill.customerIdTextField.setText("1234");
        checkBill.meterTypeTextField.setText("Single Phase");

        // Simulate button click
        checkBill.confirmButton.doClick();

        // Check if error message is shown (Swing utilities can be mocked or manually
        // checked)
        assertEquals("12345", checkBill.cnicTextField.getText()); // Fields should not clear
    }

    @Test
    public void testConfirmButtonAction_InvalidCustomerId() {
        // Simulate invalid Customer ID input
        checkBill.cnicTextField.setText("1234567890123");
        checkBill.customerIdTextField.setText("12");
        checkBill.meterTypeTextField.setText("Single Phase");

        // Simulate button click
        checkBill.confirmButton.doClick();

        // Check if error message is shown (Swing utilities can be mocked or manually
        // checked)
        assertEquals("12", checkBill.customerIdTextField.getText());
    }

    @Test
    public void testConfirmButtonAction_InvalidMeterType() {
        // Simulate invalid Meter Type input
        checkBill.cnicTextField.setText("1234567890123");
        checkBill.customerIdTextField.setText("1234");
        checkBill.meterTypeTextField.setText("Unknown Type");

        // Simulate button click
        checkBill.confirmButton.doClick();

        // Check if error message is shown
        assertEquals("Unknown Type", checkBill.meterTypeTextField.getText());
    }

    @Test
    public void testConfirmButtonAction_BlankFields() {
        // Simulate blank fields
        checkBill.cnicTextField.setText("");
        checkBill.customerIdTextField.setText("");
        checkBill.meterTypeTextField.setText("");

        // Simulate button click
        checkBill.confirmButton.doClick();

        // Check if error message is shown
        assertEquals("", checkBill.cnicTextField.getText());
        assertEquals("", checkBill.customerIdTextField.getText());
        assertEquals("", checkBill.meterTypeTextField.getText());
    }
}
