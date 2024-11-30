package experssivio.test;

import org.junit.Test;
import expressivo.*;
import expressivo.Number;

import static org.junit.Assert.*;

/**
 * Comprehensive tests for the Expression interface and its implementations.
 */
public class ExpressionTest {

    /** Test cases for Number */
    @Test
    public void testNumberEquality() {
        Expression num1 = new Number(5.0);
        Expression num2 = new Number(5.0);
        assertEquals(num1, num2);
        assertEquals(num1.hashCode(), num2.hashCode());
    }

    @Test
    public void testNumberToString() {
        Expression num = new Number(3.14159);
        assertEquals("3.14159", num.toString());
    }

    /** Test cases for Variable */
    @Test
    public void testVariableEquality() {
        Expression var1 = new Variable("x");
        Expression var2 = new Variable("x");
        assertEquals(var1, var2);
        assertEquals(var1.hashCode(), var2.hashCode());
    }

    @Test
    public void testVariableInequality() {
        Expression var1 = new Variable("x");
        Expression var2 = new Variable("y");
        assertNotEquals(var1, var2);
    }

    @Test
    public void testVariableToString() {
        Expression var = new Variable("variable");
        assertEquals("variable", var.toString());
    }

    /** Test cases for Addition */
    @Test
    public void testAdditionToStringSimple() {
        Expression add = new Addition(new Number(1), new Variable("x"));
        assertEquals("(1.0 + x)", add.toString());
    }

    @Test
    public void testAdditionToStringComplex() {
        Expression add = new Addition(new Addition(new Number(1), new Number(2)), new Variable("x"));
        assertEquals("((1.0 + 2.0) + x)", add.toString());
    }

    @Test
    public void testAdditionEquality() {
        Expression add1 = new Addition(new Number(1), new Variable("x"));
        Expression add2 = new Addition(new Number(1), new Variable("x"));
        assertEquals(add1, add2);
        assertEquals(add1.hashCode(), add2.hashCode());
    }

    @Test
    public void testAdditionInequality() {
        Expression add1 = new Addition(new Number(1), new Variable("x"));
        Expression add2 = new Addition(new Variable("x"), new Number(1)); // order matters
        assertNotEquals(add1, add2);
    }

    /** Test cases for Multiplication */
    @Test
    public void testMultiplicationToString() {
        Expression mul = new Multiplication(new Number(3), new Variable("y"));
        assertEquals("(3.0 * y)", mul.toString());
    }

    @Test
    public void testMultiplicationNested() {
        Expression mul = new Multiplication(new Number(3), new Multiplication(new Variable("x"), new Variable("y")));
        assertEquals("(3.0 * (x * y))", mul.toString());
    }

    @Test
    public void testMultiplicationEquality() {
        Expression mul1 = new Multiplication(new Number(3), new Variable("z"));
        Expression mul2 = new Multiplication(new Number(3), new Variable("z"));
        assertEquals(mul1, mul2);
        assertEquals(mul1.hashCode(), mul2.hashCode());
    }

    @Test
    public void testMultiplicationInequality() {
        Expression mul1 = new Multiplication(new Number(4), new Variable("z"));
        Expression mul2 = new Multiplication(new Variable("z"), new Number(4)); // order matters
        assertNotEquals(mul1, mul2);
    }

    /** Test cases for mixed operations */
    @Test
    public void testMixedOperationsToString() {
        Expression expr = new Addition(new Number(2), new Multiplication(new Variable("x"), new Number(3)));
        assertEquals("(2.0 + (x * 3.0))", expr.toString());
    }

    @Test
    public void testMixedOperationsEquality() {
        Expression expr1 = new Addition(new Variable("a"), new Multiplication(new Number(5), new Variable("b")));
        Expression expr2 = new Addition(new Variable("a"), new Multiplication(new Number(5), new Variable("b")));
        assertEquals(expr1, expr2);
    }

    @Test
    public void testNestedOperations() {
        Expression nested = new Addition(
                new Multiplication(new Number(2), new Addition(new Variable("x"), new Variable("y"))),
                new Number(5)
        );
        assertEquals("((2.0 * (x + y)) + 5.0)", nested.toString());
    }

    @Test
    public void testAdditionWithZero() {
        Expression add = new Addition(new Number(0), new Variable("x"));
        assertEquals("(0.0 + x)", add.toString());
    }

    @Test
    public void testMultiplicationWithOne() {
        Expression mul = new Multiplication(new Number(1), new Variable("y"));
        assertEquals("(1.0 * y)", mul.toString());
    }

    @Test
    public void testMultiplicationWithZero() {
        Expression mul = new Multiplication(new Number(0), new Variable("y"));
        assertEquals("(0.0 * y)", mul.toString());
    }

    /** HashCode and Equality Consistency */
    @Test
    public void testHashCodeConsistencyAcrossInvocations() {
        Expression expr = new Addition(new Number(7), new Variable("z"));
        int hash1 = expr.hashCode();
        int hash2 = expr.hashCode();
        assertEquals(hash1, hash2);
    }
}