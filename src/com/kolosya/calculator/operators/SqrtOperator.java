package com.kolosya.calculator.operators;

import com.kolosya.calculator.ICalculatorContext;
import com.kolosya.calculator.exceptions.CalculatorArithmeticException;
import com.kolosya.calculator.exceptions.CalculatorException;

import static java.lang.Math.sqrt;

public class SqrtOperator extends BaseScalarOperator {
    public SqrtOperator(int stackArgsCount, int commandArgsCount) {
        super(stackArgsCount, commandArgsCount);
    }

    @Override
    protected Object[] validateAndGet(ICalculatorContext<Double> context, Object... commandArgs) throws CalculatorException {
        Double num = (Double)(super.validateAndGet(context, commandArgs)[0]);
        if (num < 0) {
            context.push(num);
            throw new CalculatorArithmeticException("Sqrt by a negative number");
        }
        return new Object[]{num};
    }

    @Override
    public void perform(ICalculatorContext<Double> context, Object... commandArgs) throws CalculatorException {
        Double num = (Double)(validateAndGet(context, commandArgs)[0]);
        context.push(sqrt(num));
    }
}
