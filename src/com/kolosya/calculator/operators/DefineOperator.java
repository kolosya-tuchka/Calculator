package com.kolosya.calculator.operators;

import com.kolosya.calculator.ICalculatorContext;
import com.kolosya.calculator.exceptions.CalculatorException;
import com.kolosya.calculator.exceptions.CalculatorParserException;

import java.io.IOException;

public class DefineOperator extends BaseOperator<Double> {
    public DefineOperator(int stackArgsCount, int commandArgsCount) {
        super(stackArgsCount, commandArgsCount);
    }

    @Override
    public Object[] validateAndGet(ICalculatorContext<Double> context, Object... commandArgs) throws CalculatorException {
        super.validateAndGet(context, commandArgs);
        String name = (String)commandArgs[0];
        try {
            Double value = Double.parseDouble(commandArgs[1].toString());
            return new Object[]{name, value};
        }
        catch (NumberFormatException e) {
            throw new CalculatorParserException("Error of conversion");
        }
    }

    @Override
    public void perform(ICalculatorContext<Double> context, Object... commandArgs) throws CalculatorException, IOException {
        Object[] args = validateAndGet(context, commandArgs);
        String name = (String)args[0];
        Double value = (Double)args[1];
        context.addParam(name, value);
    }
}
