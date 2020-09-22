package com.mavallad.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();
    
    @Test
    public void testCalculateSimpleLiteral() {
        Assertions.assertEquals(4, calculator.calculate("4"));
    }

    @Test
    public void testCalculateSumAndSubstraction() {
        Assertions.assertEquals(44, calculator.calculate("6 + 40 - 2"));
    }

    @Test
    public void testCalculateSomethingMoreComplex() {
        Assertions.assertEquals(5, calculator.calculate("( 6 + 4 ) * ( 5 - 3 ) / 4"));
    }

    @Test
    public void testDivideByZero() {
        Assertions.assertThrows(DivisionByZeroException.class, () -> calculator.calculate("4 / 0"));
    }
}
