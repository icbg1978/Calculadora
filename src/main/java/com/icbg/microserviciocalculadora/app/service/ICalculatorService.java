package com.icbg.microserviciocalculadora.app.service;

import java.math.BigDecimal;

public interface ICalculatorService {

    /**
     * Method is used for calculation for given numbers and operator type
     * Calcula para los números proporcionados como argumentos el resutlado de
     * la operación
     *
     * @param operation sumar, restar...
     * @param firstNumber
     * @param secondNumber
     * @return el resultado de la operación (depende del tipo de operación). Se ha decidido que valor doble
     *              es más que suficiente para representar la operación
     */
    BigDecimal calculate(String operation, BigDecimal firstNumber, BigDecimal secondNumber);
}
