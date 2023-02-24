package com.kolosya.calculator;

import java.io.File;

public class CalculatorConfig {
    protected String operatorsConfig;
    protected String commentString;

    protected CalculatorConfig(String operatorsConfig, String commentString) {
        this.operatorsConfig = operatorsConfig;
        this.commentString = commentString;
    }
}
