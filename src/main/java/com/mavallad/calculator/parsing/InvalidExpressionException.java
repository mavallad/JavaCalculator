package com.mavallad.calculator.parsing;

public class InvalidExpressionException extends RuntimeException {
    public InvalidExpressionException(String expression) {
        super("Invalid expression '" + expression + "'");
    }
}
