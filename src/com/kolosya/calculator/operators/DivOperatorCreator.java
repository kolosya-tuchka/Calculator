package com.kolosya.calculator.operators;

public class DivOperatorCreator extends OperatorCreator<Double> {
    @Override
    public IOperator<Double> factoryMethod() {
        return new DivOperator(2, 0);
    }
}
