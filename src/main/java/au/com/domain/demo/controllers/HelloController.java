package au.com.domain.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import au.com.domain.demo.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @Autowired
    UserServices userServices;

    @RequestMapping("/")
    public String index() {
    	System.out.println("Greetings from Spring Boot!"); 
        return userServices.findById(1).getUserName();
    }

}
