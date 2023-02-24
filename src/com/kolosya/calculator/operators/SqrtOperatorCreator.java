package com.kolosya.calculator.operators;

public class SqrtOperatorCreator extends OperatorCreator<Double> {
    @Override
    public IOperator<Double> factoryMethod() {
        return new SqrtOperator(1, 0);
    }
}
