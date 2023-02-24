package com.kolosya.calculator;

import java.io.File;

public class CalculatorConfigBuilder {
    private String operatorsConfig = "operatorConfig.txt";
    private String commentString = "#";

    public CalculatorConfigBuilder setOperatorsConfig(String operatorsConfig) {
        this.operatorsConfig = operatorsConfig;
        return this;
    }

    public CalculatorConfigBuilder setCommentString(String commentString) {
        this.commentString = commentString;
        return this;
    }

    public CalculatorConfig createCalculatorConfig() {
        return new CalculatorConfig(operatorsConfig, commentString);
    }
}