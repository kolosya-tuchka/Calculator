package com.kolosya.calculator.operators;

public class PopOperatorCreator extends OperatorCreator<Double> {
    @Override
    public IOperator<Double> factoryMethod() {
        return new PopOperator(1, 0);
    }
}
