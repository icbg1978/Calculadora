package com.icbg.microserviciocalculadora.app.service;

import com.icbg.microserviciocalculadora.app.model.OperatorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class CalculatorService implements ICalculatorService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);
    @Autowired
    public CalculatorService() {
    }

    /**
     * Se intenta convertir la operación, en caso que no se pueda por no tenerla implementada se lanza un error,
     * en caso que exista se reala la operación correspondiente
     * @param operation
     * @param firstNumber
     * @param secondNumber
     * @return
     */
    @Override
    public double calculate(String operation, BigDecimal firstNumber, BigDecimal secondNumber) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Calculando resultado para : {} {} {}", operation, firstNumber, secondNumber);
        }

        OperatorType operationType = OperatorType.from(operation);

        if(operationType == null) {
            throw new RuntimeException("Imposible de procesar operación: " + operation);
        }

        switch (operationType) {
            case SUMA:
                return firstNumber.add(secondNumber).doubleValue();
            case RESTA:
                return firstNumber.subtract(secondNumber).doubleValue();
            default:
                if(LOGGER.isErrorEnabled()) {
                    LOGGER.error("Lo sentimos, dicha operación no es aún soportada para ser calculada: {}", operation);
                }
                throw new RuntimeException("Lo sentimos, dicha operación no es aún soportada para ser calculada: " + operation.toString());

        }
    }
}