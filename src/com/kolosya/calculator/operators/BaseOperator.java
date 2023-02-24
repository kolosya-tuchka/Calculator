package com.kolosya.calculator.operators;

import com.kolosya.calculator.ICalculatorContext;
import com.kolosya.calculator.exceptions.CalculatorException;
import com.kolosya.calculator.exceptions.CalculatorInputException;

public abstract class BaseOperator<DataType> implements IOperator<DataType> {
    protected int stackArgsCount = 0, commandArgsCount = 0;

    protected Object[] validateAndGet(ICalculatorContext<DataType> context, Object... commandArgs) throws CalculatorException {
        if (commandArgsCount > 0 && commandArgs == null) {
            throw new CalculatorInputException("Invalid count if arguments in command. Expected " + commandArgsCount + " but got 0.");
        }
        if (commandArgs != null && commandArgs.length != commandArgsCount) {
            throw new CalculatorInputException("Invalid count of arguments in command. Expected " + commandArgsCount + " but got " + commandArgs.length + ".");
        }
        if (context.getStackSize() < stackArgsCount) {
            throw new CalculatorInputException("Invalid count of arguments in stack. Expected " + stackArgsCount + " but got " + context.getStackSize() + ".");
        }
        return null;
    }

    public BaseOperator(int stackArgsCount, int commandArgsCount) {
        this.stackArgsCount = stackArgsCount;
        this.commandArgsCount = commandArgsCount;
    }
}
