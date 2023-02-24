package com.kolosya.calculator.operators;

public class PushOperatorCreator extends OperatorCreator<Double> {
    @Override
    public IOperator<Double> factoryMethod() {
        return new PushOperator(0, 1);
    }
}
