package org.example;

import org.junit.Test;

import org.example.parser.ExpressionParser;

import static org.junit.Assert.*;

public class ExpressionTest {

    @Test
    public void testNumber() {
        Expression num1 = new Number(5);
        Expression num2 = new Number(5.0);
        Expression num3 = new Number(10);

        // Test toString
        assertEquals("5.0", num1.toString());

        // Test equality
        assertEquals(num1, num2);
        assertNotEquals(num1, num3);

        // Test hashCode
        assertEquals(num1.hashCode(), num2.hashCode());
        assertNotEquals(num1.hashCode(), num3.hashCode());
    }

    @Test
    public void testVariable() {
        Expression var1 = new Variable("x");
        Expression var2 = new Variable("x");
        Expression var3 = new Variable("y");

        // Test toString
        assertEquals("x", var1.toString());

        // Test equality
        assertEquals(var1, var2);
        assertNotEquals(var1, var3);

        // Test hashCode
        assertEquals(var1.hashCode(), var2.hashCode());
        assertNotEquals(var1.hashCode(), var3.hashCode());

        // Test invalid variable
        assertThrows(IllegalArgumentException.class, () -> new Variable(""));
        assertThrows(IllegalArgumentException.class, () -> new Variable("123"));
        assertThrows(IllegalArgumentException.class, () -> new Variable("x123"));
    }

    @Test
    public void testAddition() {
        Expression num1 = new Number(3);
        Expression num2 = new Number(4);
        Expression addition1 = new Addition(num1, num2);
        Expression addition2 = new Addition(new Number(3), new Number(4));
        Expression addition3 = new Addition(num2, num1);

        // Test toString
        assertEquals("(3.0 + 4.0)", addition1.toString());

        // Test equality
        assertEquals(addition1, addition2);
        assertNotEquals(addition1, addition3);

        // Test hashCode
        assertEquals(addition1.hashCode(), addition2.hashCode());
        assertNotEquals(addition1.hashCode(), addition3.hashCode());
    }

    @Test
    public void testMultiplication() {
        Expression num1 = new Number(3);
        Expression num2 = new Number(4);
        Expression multiplication1 = new Multiplication(num1, num2);
        Expression multiplication2 = new Multiplication(new Number(3), new Number(4));

        // Test toString
        assertEquals("(3.0 * 4.0)", multiplication1.toString());

        // Test equality
        assertEquals(multiplication1, multiplication2);

        // Test hashCode
        assertEquals(multiplication1.hashCode(), multiplication2.hashCode());
    }

    @Test
    public void testComplexExpression() {
        Expression num1 = new Number(2);
        Expression num2 = new Number(3);
        Expression num3 = new Number(4);
        Expression var = new Variable("x");

        Expression addition = new Addition(num1, var); // (2.0 + x)
        Expression multiplication = new Multiplication(addition, num2); // ((2.0 + x) * 3.0)
        Expression complexExpression = new Addition(multiplication, num3); // (((2.0 + x) * 3.0) + 4.0)

        // Test toString
        assertEquals("(((2.0 + x) * 3.0) + 4.0)", complexExpression.toString());

        // Test structural equality
        Expression expectedExpression = new Addition(
            new Multiplication(
                new Addition(new Number(2), new Variable("x")),
                new Number(3)
            ),
            new Number(4)
        );

        assertEquals(expectedExpression, complexExpression);
    }

    @Test
    public void testParsing() {
        // Test Number parsing
        Expression num = ExpressionParser.parse("42");
        assertEquals(new Number(42), num);

        // Test Variable parsing
        Expression var = ExpressionParser.parse("x");
        assertEquals(new Variable("x"), var);

        // Test Addition parsing
        Expression addition = ExpressionParser.parse("3 + x");
        assertEquals(new Addition(new Number(3), new Variable("x")), addition);

        // Test Multiplication parsing
        Expression multiplication = ExpressionParser.parse("2 * x");
        assertEquals(new Multiplication(new Number(2), new Variable("x")), multiplication);

        // Test complex expression parsing
        Expression complex = ExpressionParser.parse("(2 + x) * 3 + 4");
        Expression expected = new Addition(
            new Multiplication(
                new Addition(new Number(2), new Variable("x")),
                new Number(3)
            ),
            new Number(4)
        );
        assertEquals(expected, complex);

        // Test invalid parsing
        assertThrows(IllegalArgumentException.class, () -> ExpressionParser.parse("2 + * 3"));
        assertThrows(IllegalArgumentException.class, () -> ExpressionParser.parse(""));
    }
}
