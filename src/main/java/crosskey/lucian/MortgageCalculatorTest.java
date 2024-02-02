package crosskey.lucian;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MortgageCalculatorTest {
    @Test
    public void testCaculateMonthlyPayment() {

        // Let's see if results are right with just a 0.01 margin with delta
        double monthlyPayment = MortgageCalculator.calculateMonthlyPayment(1000, 5, 2);
        Assertions.assertEquals(43.87, monthlyPayment, 0.01, "The monthly payment calculation is incorrect for the first test case.");

        double monthlyPayment2 = MortgageCalculator.calculateMonthlyPayment(4356, 1.27, 6);
        Assertions.assertEquals(62.87, monthlyPayment2, 0.01, "The monthly payment calculation is incorrect for the second test case.");

        /* Commented out, was just an extra test to see what happens when I input wrong expected values, of course causes the test then fails
        double monthlyPayment3 = MortgageCalculator.calculateMonthlyPayment(1000, 5, 2);
        Assertions.assertEquals(50.00, monthlyPayment3, 0.01, "The monthly payment calculation is incorrect for the first test case."); */
    }

}
