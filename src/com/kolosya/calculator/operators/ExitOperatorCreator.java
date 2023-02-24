package com.kolosya.calculator.operators;

public class ExitOperatorCreator extends OperatorCreator<Double> {

    @Override
    public IOperator<Double> factoryMethod() {
        return new ExitOperator(0, 0);
    }
}
