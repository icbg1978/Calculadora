package com.icbg.microserviciocalculadora.app.service;

import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @Before
    public void setup() throws Exception{
        this.calculatorService = new CalculatorService();
    }

    @Test
    public void OperationOk(){
        BigDecimal firstNumber = new BigDecimal(3);
        BigDecimal secondNumber = new BigDecimal(5);
        BigDecimal expected= new BigDecimal(8);
        String operation = "suma";
        BigDecimal obtain = this.calculatorService.calculate(operation , firstNumber , secondNumber );

        assertEquals(expected, obtain);
    }

    @Test
    public void OperationKo(){
        BigDecimal firstNumber = new BigDecimal(3);
        BigDecimal secondNumber = new BigDecimal(5);
       // BigDecimal expected= new BigDecimal(8);
        String operation = "multiplicacion";
        try {
            this.calculatorService.calculate(operation, firstNumber, secondNumber);
        }catch (RuntimeException exception){
            assertEquals("Operator no implemented multiplicacion", exception.getMessage());
        }
    }

}