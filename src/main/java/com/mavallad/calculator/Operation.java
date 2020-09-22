package com.mavallad.calculator;

import java.util.Optional;
import java.util.function.IntBinaryOperator;

public enum Operation {
    ADD("+", (l, r) -> l + r),
    SUBSTRACT("-", (l, r) -> l - r),
    MULTIPLY("*", (l, r) -> l * r),
    DIVIDE("/", (l, r) -> {
        if (r == 0) {
            throw new DivisionByZeroException();
        } else {
            return l / r;
        }
    });

    private final String symbol;
    private final IntBinaryOperator operation;

    private Operation(String symbol, IntBinaryOperator operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    public int operate(int left, int right) {
        return operation.applyAsInt(left, right);
    }

    public static Optional<Operation> fromText(String text) {
        for (Operation operation : Operation.values()) {
            if (operation.symbol.equals(text)) {
                return Optional.of(operation);
            }
        }
        return Optional.empty();
    }
}
