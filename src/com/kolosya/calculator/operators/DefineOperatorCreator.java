package com.kolosya.calculator.operators;

public class DefineOperatorCreator extends OperatorCreator<Double> {
    @Override
    public IOperator<Double> factoryMethod() {
        return new DefineOperator(0, 2);
    }
}
