package com.kolosya.calculator;

import com.kolosya.calculator.exceptions.CalculatorException;
import com.kolosya.calculator.exceptions.CalculatorOperatorNotFoundException;
import com.kolosya.calculator.operators.IOperator;
import com.kolosya.calculator.tests.ICalculatorTester;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

import java.util.logging.*;

class ScalarCalculator extends BaseDataCalculator<Double> implements ICalculatorTester {
    protected ScalarCalculator(InputStreamReader inputStream, PrintStream outputStream, CalculatorConfig config) throws IOException {
        super();

        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.config = config;
        this.operators = new TreeMap<>();
        this.numsStack = new Stack<>();
        this.params = new TreeMap<>();

        logger.log(Level.FINE, "Calculator constructed");
    }

    @Override
    public void init() throws CalculatorException, IOException {
        logger.log(Level.FINE, "Calculator initialized");

        int lineNum = 0;
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            lineNum++;
            String line = scanner.nextLine();
            if (line.equals("") || line.startsWith(config.commentString)) {
                continue;
            }
            try {
                process_command(line);
            }
            catch (CalculatorException e) {
                logger.log(Level.WARNING, "Error in line " + lineNum, e);
            }

            logger.log(Level.FINE, "In line " + lineNum + ":\n" + "Command " + line + " successfully performed");
        }

        logger.fine("Done!");
    }

    @Override
    public void perform(String command) throws CalculatorException, IOException {
        if (command.equals("") || command.startsWith(config.commentString)) {
            return;
        }
        process_command(command);
    }

    @Override
    public Double peek() {
        return numsStack.peek();
    }

    private void process_command(String command) throws CalculatorException, IOException {
        String[] words = command.split(" ");
        IOperator<Double> operator = operators.get(words[0]);
        if (operator == null) {
            throw new CalculatorOperatorNotFoundException("Operator " + words[0] + " not found");
        }
        String[] args = new String[words.length - 1];
        System.arraycopy(words, 1, args, 0, args.length);
        operator.perform(this, (Object[]) args);
    }

    @Override
    public InputStreamReader getInputStream() {
        return inputStream;
    }
}
