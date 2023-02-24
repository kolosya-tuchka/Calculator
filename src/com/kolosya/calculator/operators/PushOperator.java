package com.kolosya.calculator.operators;

import com.kolosya.calculator.ICalculatorContext;
import com.kolosya.calculator.exceptions.CalculatorException;
import com.kolosya.calculator.exceptions.CalculatorParameterNotFoundException;

import java.io.IOException;

public class PushOperator extends BaseOperator<Double> {
    public PushOperator(int stackArgsCount, int commandArgsCount) {
        super(stackArgsCount, commandArgsCount);
    }

    @Override
    public void perform(ICalculatorContext<Double> context, Object... commandArgs) throws CalculatorException, IOException {
        validateAndGet(context, commandArgs);
        try {
            Double num = Double.parseDouble((String)commandArgs[0]);
            context.push(num);
        } catch (NumberFormatException e) {
            String name = commandArgs[0].toString();
            Double value = context.getParam(name);
            if (value == null) {
                throw new CalculatorParameterNotFoundException("Parameter " + name + " not found");
            }
            context.push(value);
        }
    }
}
