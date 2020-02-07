package com.icbg.microserviciocalculadora;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MicroserviciocalculadoraApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MicroserviciocalculadoraApplicationTests {

    @LocalServerPort
    int randomServerPort;

    /**
     * Invoca una llamada GET localhost:(puerto_aleatorio)/calcula con los parametros de la funcion
     * @param primero
     * @param segundo
     * @param operation
     * @return
     * @throws URISyntaxException
     */
    private ResponseEntity<BigDecimal> calculate(String operation, double primero, double segundo) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort +
                "/calculate?operacion=" + operation +  "&primero=" + primero +
                "&segundo=" + segundo;
        URI uri = new URI(baseUrl);

        ResponseEntity<BigDecimal> resultado = restTemplate.getForEntity(uri, BigDecimal.class);
        return resultado;
    }

    @Test
    public void testSumaConExito() throws URISyntaxException {

        ResponseEntity<BigDecimal> resultado = calculate("suma", 4, 6);

        //Comprueba el resultado
        Assert.assertEquals(200, resultado.getStatusCodeValue());
        Assert.assertEquals(10.0d, resultado.getBody().doubleValue(), 0.001d);
    }

    @Test
    public void testRestaConÉxito() throws URISyntaxException {

        ResponseEntity<BigDecimal> resultado = calculate("resta", 4, 6);

        //Comprueba el resultado
        Assert.assertEquals(200, resultado.getStatusCodeValue());
        Assert.assertEquals(-2.0d, resultado.getBody().doubleValue(), 0.001d);
    }

    @Test
    public void testKO(){
        try{
            calculate("otra", 4, 6);
        }catch(IllegalArgumentException | URISyntaxException | HttpServerErrorException exceptionIllegal){
            Assert.assertTrue(exceptionIllegal.getMessage().contains("Operator no implemented"));

        }
    }

}
