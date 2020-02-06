# Calculadora
# How to run

## From IDE

Create a new `Run Configuration` (`Run -> Edit configurations...`) with the following data:

| Param                                 | Value          
| -------------                         |:-------------:
| _Main class_                          | _com/icbg/microserviciocalculadora/app/MicroserviciocalculadoraApplication.java

## From Maven

```bash
mvn spring-boot:run -Dspring.profiles.active=local
```

## From Fat Jar

```bash
cd /microserviciocalculadora/out/artifacts/microserviciocalculadora_jar
java -jar -Dspring.profiles.active=local
```

#How to test

## Integration Test
1.Up docker-compose.

2.Run app.

3.Run tests in IntelliJ --> Run SuiteIT like a jUnit execution.
# Calculadora Spring-Boot
Calculadora como micro-servicio (API) utilizando Spring-boot y Maven con funcionalidades básicas de sumar, restar, multiplicar y dividir.
Leer el [enunciado] asociado el proyecto (/microserviciocalculadora/enunciado.md) para más información.

Comunicación a través de una API REST (p.e. ```GET /calculate?operacion=suma&primero=2&segundo=3```)

Contiene la integración de las librerías  [tracer] enla estructura del proyecto lo que es necesario ejecutar `mvn clean install` antes de poder utilizar el proyecto.

## Requisitos previos
Necesario disponer de la ultima versión de Maven instalada. Tambien hace falta tener JAVA instalado en el sistema.

Se ha generado un Jar en /microserviciocalculadora/out/artifacts/microserviciocalculadora_jar/microserviciocalculadora.jar
 Para ejecutarlo:

`java -jar /microserviciocalculadora/out/artifacts/microserviciocalculadora_jar/microserviciocalculadora.jar`

Con estos pasos deberiamos tener a nuestra disposición una API REST escuchando en `http://localhost:8080/calculate`


## API REST
Una vez que ejecutas el proyecto, deberias tener el puerto 8080 abierto y escuchando peticiones REST GET.

Los parámetros se le deben pasar por URL en formato URLEncoded. Los parametros son los siguientes `operacion`  una cadena de texto describiendo la operación a realizar (valores aceptados `suma`, `resta`),
`primero`  es el primer numero de la operación y `segundo` el segundo numero de la operación. Por ejemplo:

`GET http://localhost:8080/calculate?operacion=suma&primero=4&segundo=9`

El resultado será un valor numerico con decimales (4 + 9 = 13):

`13`
