package au.com.domain.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
    	System.out.println("Greetings from Spring Boot!"); 
        return "Greetings from Spring Boot!";
    }

}
