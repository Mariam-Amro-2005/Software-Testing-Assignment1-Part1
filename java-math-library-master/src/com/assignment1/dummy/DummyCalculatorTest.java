package com.assignment1.dummy;

import org.junit.Test;
import static org.junit.Assert.*;

public class DummyCalculatorTest {

    private final DummyCalculator calculator = new DummyCalculator();

    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(0, calculator.add(-1, 1));
        assertEquals(-5, calculator.add(-2, -3));
    }

    @Test
    public void testDivide() {
        assertEquals(2, calculator.divide(10, 5));
        assertEquals(0, calculator.divide(0, 5));
        assertEquals(-2, calculator.divide(10, -5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        calculator.divide(10, 0);
    }

    @Test
    public void testGreetWithName() {
        assertEquals("Hello, Ahmed!", calculator.greet("Ahmed"));
    }

    @Test
    public void testGreetGuest() {
        assertEquals("Hello, Guest!", calculator.greet(null));
        assertEquals("Hello, Guest!", calculator.greet(""));
        assertEquals("Hello, Guest!", calculator.greet("   "));
    }
}