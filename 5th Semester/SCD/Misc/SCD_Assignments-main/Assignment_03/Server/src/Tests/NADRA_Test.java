package Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Controller.authentication.NADRA;
import Controller.schemas.NADRA_Data;

public class NADRA_Test {

    private NADRA nadra;
    private NADRA_Data nadraData;

    @Before
    public void setUp() {
        nadra = new NADRA();
        nadraData = new NADRA_Data("1234567890123", "2020-01-01", "2030-01-01");
    }

    @Test
    public void testAdd_Success() {
        nadra.nadra_data.clear();

        boolean result = nadra.add("9876543210987", "2022-01-01", "2032-01-01");
        assertTrue(result);
    }

    @Test
    public void testAdd_CNICAlreadyExists() {
        nadra.nadra_data.clear();
        nadra.nadra_data.add(nadraData);
        boolean result = nadra.add("1234567890123", "2022-01-01", "2032-01-01");
        assertFalse(result);
    }

    @Test
    public void testUpdate_Success() {
        nadra.nadra_data.clear();
        nadra.nadra_data.add(nadraData);

        boolean result = nadra.update("1234567890123", "2022-02-01", "2032-02-01");
        assertTrue(result);
    }

    @Test
    public void testUpdate_CNICNotFound() {
        nadra.nadra_data.clear();
        boolean result = nadra.update("1111111111111", "2022-02-01", "2032-02-01");
        assertFalse(result);
    }

    @Test
    public void testCnicExists_True() {
        nadra.nadra_data.clear();
        nadra.nadra_data.add(nadraData);

        boolean result = nadra.exists("1234567890123");
        assertTrue(result);
    }

    @Test
    public void testCnicExists_False() {
        nadra.nadra_data.clear();
        boolean result = nadra.exists("1111111111111");
        assertFalse(result);
    }

    @Test
    public void testCnicExistsExceptIndex_Success() {
        nadra.nadra_data.clear();
        nadra.nadra_data.add(nadraData);

        boolean result = nadra.cnic_exits_except_index("1234567890123", 0);
        assertFalse(result);
    }

    @Test
    public void testCnicExistsExceptIndex_Failure() {
        nadra.nadra_data.clear();
        nadra.nadra_data.add(nadraData);
        nadra.nadra_data.add(new NADRA_Data("9876543210987", "2020-01-01", "2030-01-01"));

        boolean result = nadra.cnic_exits_except_index("9876543210987", 1);
        assertFalse(result);
    }

    @Test
    public void testSaveData_Success() {
        nadra.nadra_data.clear();
        nadra.nadra_data.add(nadraData);
        nadra.save_data();
        nadra.load_data();
        int size = nadra.nadra_data.size();
        assertEquals(1, size);

        nadra.nadra_data.clear();
        nadra.save_data();
        nadra.load_data();
    }

}
