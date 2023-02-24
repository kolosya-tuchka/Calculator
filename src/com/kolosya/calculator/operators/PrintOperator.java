package com.kolosya.calculator.operators;

import com.kolosya.calculator.ICalculatorContext;
import com.kolosya.calculator.exceptions.CalculatorException;

import java.io.IOException;

public class PrintOperator extends BaseOperator<Double> {
    public PrintOperator(int stackArgsCount, int commandArgsCount) {
        super(stackArgsCount, commandArgsCount);
    }

    @Override
    public void perform(ICalculatorContext<Double> context, Object... commandArgs) throws CalculatorException, IOException {
        validateAndGet(context, commandArgs);
        Double num = context.top();
        context.getOutputStream().println(num);
    }
}
