package com.kolosya.calculator;

import com.kolosya.calculator.operators.IOperator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Stack;
import java.util.TreeMap;
import java.util.logging.*;

abstract class BaseDataCalculator<DataType> implements ICalculatorContext<DataType>, ICalculatorUser {
    protected Stack<DataType> numsStack;
    protected TreeMap<String, DataType> params;
    protected TreeMap<String, IOperator<DataType>> operators;
    protected InputStreamReader inputStream;
    protected PrintStream outputStream;
    protected CalculatorConfig config;

    protected Logger logger = Logger.getLogger("com.kolosya.calculator");

    protected BaseDataCalculator() throws IOException {
        logger.addHandler(new FileHandler("calculator.log"));
        logger.setLevel(Level.ALL);
    }

    public DataType top() {
        return numsStack.peek();
    }

    public DataType pop() {
        return numsStack.pop();
    }

    public void push(DataType data) {
        numsStack.push(data);
    }

    public DataType getParam(String name) {
        return params.get(name);
    }

    public void addParam(String name, DataType data) {
        params.merge(name, data, (oldValue, newValue) -> newValue);
    }

    public int getStackSize() {
        return numsStack.size();
    }

    public PrintStream getOutputStream() {
        return outputStream;
    }
}
