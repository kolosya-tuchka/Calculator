package com.kolosya.calculator.operators;

public class SubOperatorCreator extends OperatorCreator<Double> {
    @Override
    public IOperator<Double> factoryMethod() {
        return new SubOperator(2, 0);
    }
}
