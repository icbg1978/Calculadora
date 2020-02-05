package com.icbg.microserviciocalculadora.app.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperatorTypeTest {
    @Test
    public void getCodeOkTest(){

        String operatorSuma = "suma";
        String operatorResta = "-";

        assertEquals(OperatorType.SUMA, OperatorType.from(operatorSuma));
        assertEquals("+", OperatorType.from(operatorSuma).getCode());

        assertEquals(OperatorType.RESTA, OperatorType.from(operatorResta));
        assertEquals("-", OperatorType.from(operatorResta).getCode());
    }

    @Test
    public void getCodeKOTest(){
        String operator = "*";

        // Test
        try {
            OperatorType.from(operator);
        } catch (IllegalArgumentException notValidException) {
            assertEquals("Operator no implemented "+ operator, notValidException.getMessage());
        }

    }
}