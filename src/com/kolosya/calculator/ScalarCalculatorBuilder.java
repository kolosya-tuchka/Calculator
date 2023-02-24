package com.kolosya.calculator;

import com.kolosya.calculator.exceptions.CalculatorOperatorLoadingException;
import com.kolosya.calculator.operators.IOperator;
import com.kolosya.calculator.operators.OperatorCreator;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class ScalarCalculatorBuilder {
    private InputStreamReader inputStream = new InputStreamReader(System.in);
    private PrintStream outputStream = System.out;
    private CalculatorConfig config = new CalculatorConfigBuilder().createCalculatorConfig();

    public ScalarCalculatorBuilder setInputStream(InputStreamReader inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public ScalarCalculatorBuilder setOutputStream(PrintStream outputStream) {
        this.outputStream = outputStream;
        return this;
    }

    public ScalarCalculatorBuilder setConfig(CalculatorConfig config) {
        this.config = config;
        return this;
    }

    public ScalarCalculator createScalarCalculator() throws CalculatorOperatorLoadingException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        ScalarCalculator calculator = new ScalarCalculator(inputStream, outputStream, config);
        Scanner scanner = new Scanner(Objects.requireNonNull(calculator.getClass().getResourceAsStream(config.operatorsConfig)));
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (Objects.equals(line, "")) {
                continue;
            }
            String[] words = line.split(" ");
            if (words.length != 2) {
                throw new CalculatorOperatorLoadingException("Expected 2 words in line");
            }
            OperatorCreator<Double> operatorCreator = (OperatorCreator<Double>)Class.forName(words[1]).newInstance();
            IOperator<Double> operator = operatorCreator.factoryMethod();
            calculator.operators.merge(words[0], operator, (oldOp, newOp) -> newOp);
        }
        return calculator;
    }
}