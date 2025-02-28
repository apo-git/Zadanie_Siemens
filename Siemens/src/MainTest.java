import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    // we test here random generated string, if it is positive num.
    @Test
    public void testGenerateRandomNumbersPositive() {
        Random random = new Random();
        int length = 13;
        String number = Main.generateRandomNumbers(length, false, random);

        assertNotNull(number, "Tested string can't be null");
        assertFalse(number.startsWith("-"), "Tested string should not start with '-'");
        assertEquals(length, number.length(), "Tested string should have proper length");
        assertTrue(number.matches("[1-9][0-9]*"), "Tested string should be valid with first letter in range 0-9 and other letters in range 1-9");
    }

    // we test here random generated string, if it is negative num.
    @Test
    public void testGenerateRandomNumbersNegative() {
        Random random = new Random();
        int length = 34;
        String number = Main.generateRandomNumbers(length, true, random);

        assertNotNull(number, "Tested string can't be null");
        assertTrue(number.startsWith("-"), "Tested string should start with '-'");
        // length + 1 because of '-' sign
        assertEquals(length + 1, number.length(), "Tested string should have proper length");
        assertTrue(number.substring(1).matches("[1-9][0-9]*"), "Tested string should be valid with first letter in range 0-9 and other letters in range 1-9");
    }

    // we test if tests will pass on the smallest generated number - number 1.
    @Test
    public void testGenerateRandomNumbersMinimum() {
        Random random = new Random();
        String number = Main.generateRandomNumbers(1, false, random);

        assertNotNull(number, "Tested string can't be null");
        assertEquals(1, number.length(), "Tested string should have length 1");
        assertTrue(number.matches("[1-9]"), "Tested string should be num (String) between 1 and 9");
    }

    // we test if tests will pass on the biggest generated number - number 50
    @Test
    public void testGenerateRandomNumbersMaximum() {
        Random random = new Random();
        int length = 50;
        String number = Main.generateRandomNumbers(length, true, random);

        assertNotNull(number, "Tested string can't be null");
        // we set isNegative parameter of method to true, that's why we expect '-' sign at beginning
        assertTrue(number.startsWith("-"), "Tested string should start with '-'");
        assertEquals(length + 1, number.length(), "Tested string should have proper length (of 51 because of '-'");
        assertTrue(number.substring(1).matches("[1-9][0-9]*"), "Tested string should be valid with first letter in range 0-9 and other letters in range 1-9");
    }

    @Test
    void testCompare() {
        assertTrue(Main.compare("713", "714") < 0);
        assertTrue(Main.compare("714", "713") > 0);
        assertTrue(Main.compare("7142", "714") > 0);
        assertTrue(Main.compare("714", "7142") < 0);
        assertEquals(0, Main.compare("0", "0"));
    }

    @Test
    public void testAddPositive() {
        assertEquals("960", Main.addPositive("856", "104"));
        assertEquals("912435778110", Main.addPositive("912312321321", "123456789"));
        assertEquals("0", Main.addPositive("0", "0"));
        assertEquals("5", Main.addPositive("5", "0"));
        assertEquals("5", Main.addPositive("0", "5"));
        assertEquals("444", Main.addPositive("123", "321"));
    }
}