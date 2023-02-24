package com.kolosya.calculator.operators;

import com.kolosya.calculator.ICalculatorContext;
import com.kolosya.calculator.exceptions.CalculatorException;

import java.io.IOException;

public class PopOperator extends BaseOperator<Double> {
    public PopOperator(int stackArgsCount, int commandArgsCount) {
        super(stackArgsCount, commandArgsCount);
    }

    @Override
    public void perform(ICalculatorContext<Double> context, Object... commandArgs) throws CalculatorException, IOException {
        validateAndGet(context, commandArgs);
        context.pop();
    }
}
