package org.example;

public interface Expression {
    public static Expression parse(String input) {
        throw new RuntimeException("unimplemented");
    }

    @Override
    public String toString();

    @Override
    public boolean equals(Object thatObject);


    @Override
    public int hashCode();
}
