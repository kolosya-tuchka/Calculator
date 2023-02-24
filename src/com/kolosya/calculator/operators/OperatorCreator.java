package com.kolosya.calculator.operators;

public abstract class OperatorCreator<DataType> {
    public abstract IOperator<DataType> factoryMethod();
}
