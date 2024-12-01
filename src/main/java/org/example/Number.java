package org.example;

import java.util.Objects;

/**
 * Represents a constant number in the expression.
 */
public class Number implements Expression {
    private final double value;

    public Number(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Number)) return false;
        Number other = (Number) obj;
        return Double.compare(value, other.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}