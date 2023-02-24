package com.kolosya.calculator;

import java.io.InputStreamReader;
import java.io.PrintStream;

public interface ICalculatorContext<DataType> {
    public DataType top();
    public DataType pop();
    public void push(DataType data);
    public void addParam(String name, DataType data);
    public DataType getParam(String name);
    public int getStackSize();
    public PrintStream getOutputStream();
    public InputStreamReader getInputStream();
}
