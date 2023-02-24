package com.kolosya.calculator;

import com.kolosya.calculator.exceptions.CalculatorException;
import com.kolosya.calculator.exceptions.CalculatorOperatorNotFoundException;

import java.io.IOException;

public interface ICalculatorUser {
    public void init() throws CalculatorException, IOException;
}
