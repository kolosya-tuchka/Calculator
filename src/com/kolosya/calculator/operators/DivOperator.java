package com.kolosya.calculator.operators;

import com.kolosya.calculator.ICalculatorContext;
import com.kolosya.calculator.exceptions.CalculatorArithmeticException;
import com.kolosya.calculator.exceptions.CalculatorException;

public class DivOperator extends BaseScalarOperator {
    public DivOperator(int stackArgsCount, int commandArgsCount) {
        super(stackArgsCount, commandArgsCount);
    }

    @Override
    protected Double[] validateAndGet(ICalculatorContext<Double> context, Object... commandArgs) throws CalculatorException {
        Double[] result = (Double[])super.validateAndGet(context, commandArgs);
        if (result[1] == 0) {
            throw new CalculatorArithmeticException("Division by zero");
        }
        return result;
    }

    @Override
    public void perform(ICalculatorContext<Double> context, Object... commandArgs) throws CalculatorException {
        Double[] nums = validateAndGet(context, commandArgs);
        context.push(nums[0] / nums[1]);
    }
}
