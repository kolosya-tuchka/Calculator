package com.kolosya.calculator.operators;

import com.kolosya.calculator.ICalculatorContext;
import com.kolosya.calculator.exceptions.CalculatorException;

public class PlusOperator extends BaseScalarOperator {
    public PlusOperator(int stackArgsCount, int commandArgsCount) {
        super(stackArgsCount, commandArgsCount);
    }

    @Override
    public void perform(ICalculatorContext<Double> context, Object... commandArgs) throws CalculatorException {
        Double[] nums = (Double[]) validateAndGet(context, commandArgs);
        context.push(nums[0] + nums[1]);
    }
}
