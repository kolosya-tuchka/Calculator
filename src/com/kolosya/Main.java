package com.kolosya;

import com.kolosya.calculator.*;
import com.kolosya.calculator.exceptions.CalculatorException;

import java.io.*;

public class Main {

    public static void main(String[] args) throws CalculatorException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        InputStream input = args.length > 0 ? new FileInputStream(args[0]) : System.in;
        PrintStream output = args.length > 1 ? new PrintStream(args[1]) : System.out;

        ICalculatorUser calculatorUser = new ScalarCalculatorBuilder()
                .setInputStream(new InputStreamReader(input))
                .setOutputStream(output)
                .createScalarCalculator();

        calculatorUser.init();
    }
}
