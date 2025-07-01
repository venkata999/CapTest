import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ven.PolishNotationCalculator;
import com.ven.exception.PolishNotationException;

public class PolishNotationCalculatorTest {

    private List<String> getListFromString(String str) {
        return Arrays.asList(str.toString().split(" "));
    }

    @Test
    void rpnCalculate() throws PolishNotationException {
        String calStr = "2 4 * 2 +";
        assertEquals(10, PolishNotationCalculator.rpnCalculate(getListFromString(calStr)));
    }

    @Test
    void rpnCalculateWithMinus() throws PolishNotationException {
        String calStr = "12 4 * 2 -";
        assertEquals(46, PolishNotationCalculator.rpnCalculate(getListFromString(calStr)));
    }

    @Test
    void rpnCalculateWithSqrt() throws PolishNotationException {
        String calStr = "10 4 * 2 + 7 + sqrt";
        assertEquals(7, PolishNotationCalculator.rpnCalculate(getListFromString(calStr)));
    }

    @Test
    void rpnCalculateWithSin() throws PolishNotationException {
        String calStr = "10 4 * 2 + 7 + sin";
        assertEquals(-0.9537526527594719, PolishNotationCalculator.rpnCalculate(getListFromString(calStr)));
    }

    @Test
    void rpnCalculateWithCos() throws PolishNotationException {
        String calStr = "10 4 * 2 + 7 + cos";
        assertEquals(+0.3005925437436371, PolishNotationCalculator.rpnCalculate(getListFromString(calStr)));
    }

    @Test
    void rpnCalculateWithMod1() throws PolishNotationException {
        String calStr = "10 4 * 9 + 7 mod";
        assertEquals(0.0, PolishNotationCalculator.rpnCalculate(getListFromString(calStr)));
    }

    @Test
    void rpnCalculateWithMod2() throws PolishNotationException {
        String calStr = "10 4 * 9 + 9 mod";
        assertEquals(4.0, PolishNotationCalculator.rpnCalculate(getListFromString(calStr)));
    }

    @Test
    void rpnCalculateWithDivide() throws PolishNotationException {
        String calStr = "55 1 + 9 + 5 /";
        assertEquals(13.0, PolishNotationCalculator.rpnCalculate(getListFromString(calStr)));
    }

    @Test
    void rpnCalculateWithException() {
        String calStr = "1A 4 * 9 + 9 -";
        Exception exception = assertThrows(PolishNotationException.class, () -> {
            PolishNotationCalculator.rpnCalculate(getListFromString(calStr));
        });
        assertTrue(exception.getMessage().contains("User entered incorrect input"));

    }

    @Test
    void calculateTestWithPlus() {
        assertEquals(14.0, PolishNotationCalculator.calculate("+", 2, 12));
    }

    @Test
    void calculateTestWithAverage() {
        assertEquals(10.5, PolishNotationCalculator.calculate("avg", 12, 9));
    }

    @Test
    void calculateTestWithSquareRoot() {
        assertEquals(11.0, PolishNotationCalculator.calculate("sqrt", 121, 0));
    }

}
