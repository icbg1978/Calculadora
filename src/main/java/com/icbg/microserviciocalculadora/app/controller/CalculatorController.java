package com.icbg.microserviciocalculadora.app.controller;

import com.icbg.microserviciocalculadora.app.service.ICalculatorService;
import io.corp.calculator.TracerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CalculatorController {

    private ICalculatorService calculatorService;
    private TracerImpl tracer = new TracerImpl();

    @Autowired
    public CalculatorController(ICalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping(path= "/calculate")
    public ResponseEntity<BigDecimal> calculate(@RequestParam(name = "operacion") String operacion,
                                            @RequestParam(name = "primero") BigDecimal primerNumero,
                                            @RequestParam(name = "segundo") BigDecimal segundoNumero) {

        BigDecimal result = this.calculatorService.calculate(operacion, primerNumero, segundoNumero);
        tracer.trace(operacion+" "+primerNumero+" "+segundoNumero+" = "+result);
        return result != null ?
                new ResponseEntity<BigDecimal>(result, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
