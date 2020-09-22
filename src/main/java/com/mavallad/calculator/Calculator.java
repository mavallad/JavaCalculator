package com.mavallad.calculator;

import com.mavallad.calculator.parsing.ExpressionParser;

public class Calculator {
    private final ExpressionParser expressionParser = new ExpressionParser();

    public int calculate(String text) {
        return expressionParser.parse(text).calculate();
    }
}
