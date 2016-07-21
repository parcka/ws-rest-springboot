package controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import entities.Operation;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by XTR100 on 28/06/2016.
 */

@RestController
@EnableAutoConfiguration
@SpringBootApplication
@RequestMapping("/rest")
public class CalculatorController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping(value = "/sum", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    String sum(@RequestBody String payload) {
        System.out.println("Request BOdy es: " + payload);

        JSONObject jsonObject = new JSONObject(payload);
        Operation operation = new Operation();

        operation = calculate(
                parsePayloadToOperation(payload));


        JSONObject jsonResponse = new JSONObject(operation);
        return jsonResponse.toString();
    }

    @RequestMapping(value = "/sum", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    @ResponseBody
    String prueba() {
        Operation operation = new Operation();
        operation.setResult(100);
        JSONPObject jsonpObject = new JSONPObject("result", operation);
        return jsonpObject.getValue().toString();
    }

    @RequestMapping(value = "/operate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    Operation operate(Operation operation) {
        operation.setResult(operation.getNumberA() +
                operation.getNumberB());
        return operation;
    }

    Operation substraction() {
        return null;
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(CalculatorController.class, args);

        try {
        } finally {

        }
    }

    private Operation parsePayloadToOperation(String json) {
        Operation operation = new Operation();
        JSONObject jsonObject = new JSONObject(json);

        operation.setNumberA((Integer) jsonObject.get("numberA"));
        operation.setNumberB((Integer) jsonObject.get("numberB"));
        operation.setOperatorSimbol((String) jsonObject.get("operatorSimbol"));



        return operation;

    }

    private Operation calculate(Operation operation) {
        Integer result = null;
        Integer numberA = operation.getNumberA();
        Integer numberB = operation.getNumberB();
        String operatorSimbol = operation.getOperatorSimbol();

        switch (operatorSimbol) {
            case "+":
                result = numberA + numberB;
                break;
            case "-":
                result = numberA - numberB;
                break;
            case "*":
                result = numberA * numberB;
                break;
            case "/":
                result = numberA / numberB;
                break;

        }
        operation.setResult(result);
        return operation;
    }

}
