package com.kolosya.calculator.tests;

import com.kolosya.calculator.exceptions.CalculatorException;
import com.kolosya.calculator.exceptions.CalculatorOperatorNotFoundException;

import java.io.IOException;

public interface ICalculatorTester {
    public void perform(String command) throws CalculatorException, IOException;
    public Double peek();
}
