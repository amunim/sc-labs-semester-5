/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */

package expressivo;

/**
 * Expression represents an immutable mathematical expression.
 * This interface is implemented by Number, Variable, Addition, and Multiplication.
 */
public interface Expression {
    /**
     * Parse a mathematical expression into an Expression object.
     * @param input string input representing the expression
     * @return an Expression object
     * @throws IllegalArgumentException if the input is invalid
     */
    public static Expression parse(String input) {
        // Parsing logic to create the appropriate Expression subclass instance
        // Placeholder: Actual implementation required
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
     * @return a parsable string representation of the expression
     */
    @Override
    public String toString();

    /**
     * Compares structural equality of expressions.
     * @param obj another object
     * @return true if this expression is structurally equal to obj
     */
    @Override
    public boolean equals(Object obj);

    /**
     * @return hash code consistent with equals() for structural equality
     */
    @Override
    public int hashCode();
}

