package com.kolosya.calculator.operators;

import com.kolosya.calculator.ICalculatorContext;
import com.kolosya.calculator.exceptions.CalculatorException;

public abstract class BaseScalarOperator extends BaseOperator<Double> {
    public BaseScalarOperator(int stackArgsCount, int commandArgsCount) {
        super(stackArgsCount, commandArgsCount);
    }

    protected Object[] validateAndGet(ICalculatorContext<Double> context, Object... commandArgs) throws CalculatorException {
        super.validateAndGet(context, commandArgs);
        Double[] result = new Double[stackArgsCount];
        for (int i = 0; i < stackArgsCount; ++i) {
            result[i] = context.pop();
        }
        return result;
    }
}
