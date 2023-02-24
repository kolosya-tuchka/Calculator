package com.kolosya.calculator.operators;

public class PrintOperatorCreator extends OperatorCreator<Double> {
    @Override
    public IOperator<Double> factoryMethod() {
        return new PrintOperator(1, 0);
    }
}
