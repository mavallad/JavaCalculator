package com.mavallad.calculator;

import com.mavallad.calculator.parsing.ExpressionParser;
import com.mavallad.calculator.parsing.InvalidExpressionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpressionParserTest {
    private final ExpressionParser parser = new ExpressionParser();

    @Test
    public void testParseLiteral() {
        Expression expected = new Literal(5);
        Assertions.assertEquals(expected, parser.parse("5"));
    }

    @Test
    public void testParseSumAndSubstract() {
        Expression expected = new ExpressionTree(Operation.SUBSTRACT,
                new ExpressionTree(
                        Operation.SUM,
                        new Literal(2),
                        new Literal(5)),
                new Literal(4));
        Assertions.assertEquals(expected, parser.parse("2 + 5 - 4"));
    }

    @Test
    public void testParseSumASubstractAndMultiplyWithParenthesis() {
        Expression expected = new ExpressionTree(
                Operation.MULTIPLY,
                new ExpressionTree(
                        Operation.SUM,
                        new Literal(2),
                        new ExpressionTree(
                                Operation.SUBSTRACT,
                                new Literal(5),
                                new Literal(4))),
                new Literal(3));
        Assertions.assertEquals(expected, parser.parse("2 + ( 5 - 4 ) * 3"));
    }

    @Test
    public void testParseExpressionWithParenthesis() {
        Expression expected = new ExpressionTree(
                Operation.SUM,
                new Literal(2),
                new Literal(3));
        Assertions.assertEquals(expected, parser.parse("( 2 + 3 )"));
    }

    @Test
    public void testParseDoubleParenthesis() {
        Expression expected = new ExpressionTree(
                Operation.MULTIPLY,
                new ExpressionTree(
                        Operation.SUM,
                        new ExpressionTree(
                                Operation.SUM,
                                new Literal(2),
                                new Literal(3)),
                        new Literal(4)),
                new Literal(2));
        Assertions.assertEquals(expected, parser.parse("( ( 2 + 3 ) + 4 ) * 2"));
    }

    @Test()
    public void testParseExpressionWithLetter() {
        Assertions.assertThrows(InvalidExpressionException.class, () -> parser.parse("a + 3"));
    }

    @Test()
    public void testParseExpressionWithEmptyTokens() {
        Assertions.assertThrows(InvalidExpressionException.class, () -> parser.parse(" "));
    }
}
