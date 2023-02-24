package com.kolosya.calculator.operators;

import com.kolosya.calculator.ICalculatorContext;
import com.kolosya.calculator.exceptions.CalculatorException;

import java.io.IOException;

public class ExitOperator extends BaseOperator<Double> {

    public ExitOperator(int stackArgsCount, int commandArgsCount) {
        super(stackArgsCount, commandArgsCount);
    }

    @Override
    public void perform(ICalculatorContext<Double> context, Object... commandArgs) throws CalculatorException, IOException {
        validateAndGet(context, commandArgs);
        context.getInputStream().close();
    }
}
