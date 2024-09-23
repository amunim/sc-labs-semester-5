/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package rules;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit tests for RulesOf6005.
 */
public class RulesOf6005Test {
    
    /**
     * Tests the mayUseCodeInAssignment method.
     */
    @Test
    public void testMayUseCodeInAssignment() {
        // Test case 1: Un-cited publicly-available code
        assertFalse("Expected false: un-cited publicly-available code",
                RulesOf6005.mayUseCodeInAssignment(false, true, false, false, false));
        
        // Test case 2: Self-written required code
        assertTrue("Expected true: self-written required code",
                RulesOf6005.mayUseCodeInAssignment(true, false, true, true, true));
        
        // Test case 3: Coursework not cited
        assertFalse("Expected false: coursework not cited",
                RulesOf6005.mayUseCodeInAssignment(false, true, true, false, false));
        
        // Test case 5: Publicly available code without implementation required
        assertTrue("Expected true: publicly available code, not implementation required and cited",
                RulesOf6005.mayUseCodeInAssignment(false, true, false, true, false));
        
        // Test case 6: Publicly available code with implementation required
        assertFalse("Expected false: publicly available code with implementation required",
                RulesOf6005.mayUseCodeInAssignment(false, true, false, true, true));
        
        // Test case 7: Code not available to others
        assertFalse("Expected false: code not available to others and not self-written",
                RulesOf6005.mayUseCodeInAssignment(false, false, false, true, false));
        
        // Test case 8: Self-written code not required
        assertTrue("Expected true: self-written code even if not required",
                RulesOf6005.mayUseCodeInAssignment(true, false, false, false, false));
    }
}