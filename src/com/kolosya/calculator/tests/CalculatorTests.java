package com.kolosya.calculator.tests;

import com.kolosya.calculator.CalculatorConfig;
import com.kolosya.calculator.CalculatorConfigBuilder;
import com.kolosya.calculator.ScalarCalculatorBuilder;
import com.kolosya.calculator.exceptions.*;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculatorTests {
    public ICalculatorTester testCalculator;

    public CalculatorTests() throws CalculatorOperatorLoadingException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        testCalculator = new ScalarCalculatorBuilder().
                setConfig(new CalculatorConfigBuilder().
                        setCommentString("#").
                        setOperatorsConfig("operatorConfig.txt").
                        createCalculatorConfig())
                .createScalarCalculator();
    }

    @Test
    public void testPush() throws CalculatorException, IOException {
        testCalculator.perform("push 123");
        assertEquals(new Double(123.0), testCalculator.peek());
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("push"));
        assertThrows(CalculatorParameterNotFoundException.class, () -> testCalculator.perform("push bruh"));
    }

    @Test
    public void testDefine() throws CalculatorException, IOException {
        testCalculator.perform("define a 5");
        testCalculator.perform("define b 10");
        testCalculator.perform("push a");
        testCalculator.perform("push b");
        testCalculator.perform("define a 2");
        testCalculator.perform("push a");

        assertEquals(testCalculator.peek(), new Double(2.0));
        testCalculator.perform("pop");
        assertEquals(testCalculator.peek(), new Double(10.0));
        testCalculator.perform("pop");
        assertEquals(testCalculator.peek(), new Double(5.0));
        testCalculator.perform("pop");

        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("define"));
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("define a"));
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("define 10"));
        assertThrows(CalculatorParserException.class, () -> testCalculator.perform("define 10 a"));
    }

    @Test
    public void testSum() throws CalculatorException, IOException {
        testCalculator.perform("push 5");
        testCalculator.perform("push 7");
        testCalculator.perform("sum");
        assertEquals(new Double(12.0), (Double)testCalculator.peek());
        testCalculator.perform("push -12");
        testCalculator.perform("+");
        assertEquals(new Double(0.0), (Double)testCalculator.peek());
        testCalculator.perform("pop");

        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("+"));
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("sum"));
        testCalculator.perform("push 10");
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("+"));
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("sum"));
        testCalculator.perform("pop");
    }

    @Test
    public void testSub() throws CalculatorException, IOException {
        testCalculator.perform("push 5");
        testCalculator.perform("push 7");
        testCalculator.perform("sub");
        assertEquals(new Double(2.0), (Double)testCalculator.peek());
        testCalculator.perform("push -12");
        testCalculator.perform("-");
        assertEquals(new Double(-14.0), (Double)testCalculator.peek());
        testCalculator.perform("pop");

        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("-"));
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("sub"));
        testCalculator.perform("push 10");
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("-"));
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("sub"));
        testCalculator.perform("pop");
    }

    @Test
    public void testMul() throws CalculatorException, IOException {
        testCalculator.perform("push 15");
        testCalculator.perform("push 15");
        testCalculator.perform("mul");
        assertEquals(new Double(225.0), testCalculator.peek());
        testCalculator.perform("push 0");
        testCalculator.perform("*");
        assertEquals(new Double(0.0), testCalculator.peek());
        testCalculator.perform("pop");

        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("*"));
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("mul"));
        testCalculator.perform("push 10");
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("*"));
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("mul"));
        testCalculator.perform("pop");
    }

    @Test
    public void testDiv() throws CalculatorException, IOException {
        testCalculator.perform("push 10");
        testCalculator.perform("push 2");
        testCalculator.perform("div");
        assertEquals(new Double(0.2), testCalculator.peek());
        testCalculator.perform("pop");
        testCalculator.perform("push 0");
        testCalculator.perform("push 1");
        assertThrows(CalculatorArithmeticException.class, () -> testCalculator.perform("/"));

        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("/"));
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("div"));
        testCalculator.perform("push 10");
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("/"));
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("div"));
        testCalculator.perform("pop");
    }

    @Test
    public void testSqrt() throws CalculatorException, IOException {
        testCalculator.perform("push 4");
        testCalculator.perform("sqrt");
        assertEquals(new Double(2.0), testCalculator.peek());
        testCalculator.perform("pop");
        testCalculator.perform("push -1");

        assertThrows(CalculatorArithmeticException.class, () -> testCalculator.perform("sqrt"));
        testCalculator.perform("pop");
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("sqrt"));
    }

    @Test
    public void testOperatorNotFoundException() throws CalculatorException, IOException {
        assertThrows(CalculatorOperatorNotFoundException.class, () -> testCalculator.perform("test"));
        assertThrows(CalculatorOperatorNotFoundException.class, () -> testCalculator.perform("operator 10"));
        assertThrows(CalculatorOperatorNotFoundException.class, () -> testCalculator.perform("calc c"));
    }

    @Test
    public void testPrint() throws CalculatorException, IOException {
        testCalculator.perform("define a 3");
        testCalculator.perform("push a");
        testCalculator.perform("print");
        assertEquals(new Double(3.0), testCalculator.peek());

        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("print 3"));
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("print a"));
    }

    @Test
    public void testComment() throws CalculatorException, IOException {
        testCalculator.perform("");
        testCalculator.perform("#kldsalfsd;lfka;l");
        testCalculator.perform("#push 5");
        assertThrows(CalculatorInputException.class, () -> testCalculator.perform("pop"));
    }
}
