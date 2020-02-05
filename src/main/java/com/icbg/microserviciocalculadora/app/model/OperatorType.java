package com.icbg.microserviciocalculadora.app.model;

import java.util.Arrays;

public enum OperatorType {
    /*TODO add more operations in the futureo*/

    SUMA("+"),
    RESTA("-");

    private String code;

    OperatorType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static OperatorType from(String code) {
        return Arrays.stream(OperatorType.values())
                .filter(operatorType -> (operatorType.getCode().equalsIgnoreCase(code) || operatorType.name().equalsIgnoreCase(code)))
                .findFirst()
                .orElseThrow(() ->buildIllegalArgumentException(code));
    }

    private static IllegalArgumentException buildIllegalArgumentException(String operator) {
        return new IllegalArgumentException("Operator no implemented " + operator);
    }
}
