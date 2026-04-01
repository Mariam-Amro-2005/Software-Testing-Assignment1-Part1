package com.assignment1.dummy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class DummyCalculatorTest {

    private final DummyCalculator calculator = new DummyCalculator();

    @Test
    @DisplayName("Should add two numbers correctly")
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(0, calculator.add(-1, 1));
        assertEquals(-5, calculator.add(-2, -3));
    }

    @Test
    @DisplayName("Should divide two numbers correctly")
    void testDivide() {
        assertEquals(2, calculator.divide(10, 5));
        assertEquals(0, calculator.divide(0, 5));
        assertEquals(-2, calculator.divide(10, -5));
    }

    @Test
    @DisplayName("Should throw exception when dividing by zero")
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0);
        });
    }

    @Test
    @DisplayName("Should greet with name")
    void testGreetWithName() {
        assertEquals("Hello, Ahmed!", calculator.greet("Ahmed"));
    }

    @Test
    @DisplayName("Should greet guest when name is null or empty")
    void testGreetGuest() {
        assertEquals("Hello, Guest!", calculator.greet(null));
        assertEquals("Hello, Guest!", calculator.greet(""));
        assertEquals("Hello, Guest!", calculator.greet("   "));
    }
}