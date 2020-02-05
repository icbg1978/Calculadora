package com.icbg.microserviciocalculadora.app.controller;

import com.icbg.microserviciocalculadora.app.model.Greeting;
import com.icbg.microserviciocalculadora.app.service.ICalculatorService;
import io.corp.calculator.TracerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CalculatorController {
    private static final String template = "Hola, %s!";
    private final AtomicLong counter = new AtomicLong();
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "Inma") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    private ICalculatorService calculatorService;
    private TracerImpl tracer = new TracerImpl();
    //private ResultOperation resultOperation;
    @Autowired
    public CalculatorController(ICalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping(path= "/calculate")
    public ResponseEntity<Double> calculate(@RequestParam(name = "operacion") String operacion,
                                            @RequestParam(name = "primero") BigDecimal primerNumero,
                                            @RequestParam(name = "segundo") BigDecimal segundoNumero) {

        double result = this.calculatorService.calculate(operacion, primerNumero, segundoNumero);
        tracer.trace(result);
        return !Double.isNaN(result) ?
                new ResponseEntity<Double>(result, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
