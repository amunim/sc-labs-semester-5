package experssivio.test;

import org.junit.Test;
import expressivo.*;
import expressivo.Number;

import static org.junit.Assert.*;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    @Test
    public void testToString() {
        Expression num = new Number(5);
        Expression var = new Variable("x");
        Expression add = new Addition(num, var);
        Expression mul = new Multiplication(add, new Number(3));

        assertEquals("5.0", num.toString());
        assertEquals("x", var.toString());
        assertEquals("(5.0 + x)", add.toString());
        assertEquals("((5.0 + x) * 3.0)", mul.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        Expression num1 = new Number(5);
        Expression num2 = new Number(5);
        Expression var1 = new Variable("x");
        Expression var2 = new Variable("x");
        Expression add1 = new Addition(num1, var1);
        Expression add2 = new Addition(num2, var2);

        assertEquals(num1, num2);
        assertEquals(var1, var2);
        assertEquals(add1, add2);
        assertEquals(add1.hashCode(), add2.hashCode());
    }
}
