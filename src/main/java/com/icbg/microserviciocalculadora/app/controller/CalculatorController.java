package com.icbg.microserviciocalculadora.app.controller;

import com.icbg.microserviciocalculadora.app.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CalculatorController {
    private static final String template = "Hola, %s!";
    private final AtomicLong counter = new AtomicLong();
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "Inma") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
