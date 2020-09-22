package com.mavallad.calculator.parsing;

public class Consumed<T> {
    public final T element;
    public final String remainingText;

    public Consumed(T element, String remainingText) {
        this.element = element;
        this.remainingText = remainingText;
    }
}
