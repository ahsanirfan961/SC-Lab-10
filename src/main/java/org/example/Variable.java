package org.example;

import java.util.Objects;

/**
 * Represents a variable in the expression.
 */
public class Variable implements Expression {
    private final String name;

    public Variable(String name) {
        if (name.isEmpty() || !name.chars().allMatch(Character::isLetter)) {
            throw new IllegalArgumentException("Variable name must be non-empty and contain only letters");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Variable)) return false;
        Variable other = (Variable) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}