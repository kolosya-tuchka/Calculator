package com.kolosya.calculator.operators;

public class PlusOperatorCreator extends OperatorCreator<Double> {

    @Override
    public IOperator<Double> factoryMethod() {
        return new PlusOperator(2, 0);
    }
}
