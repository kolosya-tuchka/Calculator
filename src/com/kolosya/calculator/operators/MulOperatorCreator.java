package com.kolosya.calculator.operators;

public class MulOperatorCreator extends OperatorCreator<Double> {

    @Override
    public IOperator<Double> factoryMethod() {
        return new MulOperator(2, 0);
    }
}
