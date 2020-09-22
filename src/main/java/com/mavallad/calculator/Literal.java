package com.mavallad.calculator;

public class Literal extends Expression {
    private final int value;

    public Literal(int value) {
        this.value = value;
    }

    @Override
    public int calculate() {
        return value;
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
        final Literal other = (Literal) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

}
