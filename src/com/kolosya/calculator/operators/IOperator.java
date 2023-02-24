package com.kolosya.calculator.operators;

import com.kolosya.calculator.ICalculatorContext;
import com.kolosya.calculator.exceptions.CalculatorException;

import java.io.IOException;

public interface IOperator<DataType> {
    public void perform(ICalculatorContext<DataType> context, Object... commandArgs) throws CalculatorException, IOException;
}
