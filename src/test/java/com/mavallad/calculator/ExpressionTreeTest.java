package com.mavallad.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpressionTreeTest {
    
    @Test
    // Calculation of 4 +2 == 6
    public void testCalculateSimpleSum() {
        Expression expression = new ExpressionTree(Operation.ADD, new Literal(4), new Literal(2));
        Assertions.assertEquals(6, expression.calculate());
    }

    @Test
    // Calculation of 4 + (2 - 7) == -1
    public void testCalculateSumAndSubstract() {
        Expression expression = new ExpressionTree(
                Operation.ADD,
                new Literal(4),
                new ExpressionTree(
                        Operation.SUBSTRACT,
                        new Literal(2),
                        new Literal(7)));
        Assertions.assertEquals(-1, expression.calculate());
    }

    @Test
    public void testCalculateSumAndMultiply() {
        Expression expression = new ExpressionTree(
                Operation.MULTIPLY,
                new Literal(4),
                new ExpressionTree(
                        Operation.ADD,
                        new Literal(2),
                        new Literal(7)));
        Assertions.assertEquals(36, expression.calculate());
    }
}
