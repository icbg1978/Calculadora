package com.icbg.microserviciocalculadora.app.service;

import com.icbg.microserviciocalculadora.app.model.OperatorType;
import io.corp.calculator.TracerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import java.math.BigDecimal;
@Service
public class CalculatorService implements ICalculatorService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);
    private TracerImpl tracer = new TracerImpl();

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
    public BigDecimal calculate(String operation, BigDecimal firstNumber, BigDecimal secondNumber) {

        if (LOGGER.isDebugEnabled()) {
            tracer.trace("Calculando resultado para : "+ operation+" "+firstNumber+" "+secondNumber);
            LOGGER.debug("Calculando resultado para : {} {} {}", operation, firstNumber, secondNumber);
        }

        OperatorType operationType = OperatorType.from(operation);

        if(operationType == null) {
            tracer.trace("Imposible de procesar operación: " + operation);
            throw new RuntimeException("Imposible de procesar operación: " + operation);
        }

        switch (operationType) {
            case SUMA:
                return firstNumber.add(secondNumber);
            case RESTA:
                return firstNumber.subtract(secondNumber);
            default:
                if(LOGGER.isErrorEnabled()) {
                    tracer.trace("Lo sentimos, dicha operación no es aún soportada para ser calculada: "+ operation);
                    LOGGER.error("Lo sentimos, dicha operación no es aún soportada para ser calculada: {}", operation);
                }
                throw new RuntimeException("Lo sentimos, dicha operación no es aún soportada para ser calculada: " + operation.toString());

        }
    }
}