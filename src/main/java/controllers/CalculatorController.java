package controllers;

import entities.Operation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/sum",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ResponseBody
    Operation sum() {
        return null;
    }

    Operation substraction() {
        return null;
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(CalculatorController.class, args);
    }

}
