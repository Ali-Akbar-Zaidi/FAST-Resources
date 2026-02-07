import org.junit.Test;
import static org.junit.Assert.*;

class CalculatorTest {

    @Test
    void testAddition() {
        Calculator calc = new Calculator();
        int result = calc.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    void testMultiplication() {
        Calculator calc = new Calculator();
        int result = calc.multiply(4, 5);
        assertEquals(20, result);
    }
}
