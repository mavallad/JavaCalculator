package com.mavallad.calculator;

import java.util.Objects;

public class ExpressionTree extends Expression {
    private final Operation operation;
    private final Expression left;
    private final Expression right;

    public ExpressionTree(Operation operation, Expression left, Expression right) {
        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    @Override
    public int calculate() {
        return operation.operate(left.calculate(), right.calculate());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExpressionTree other = (ExpressionTree) obj;
        if (this.operation != other.operation) {
            return false;
        }
        if (!Objects.equals(this.left, other.left)) {
            return false;
        }
        if (!Objects.equals(this.right, other.right)) {
            return false;
        }
        return true;
    }

    
}
