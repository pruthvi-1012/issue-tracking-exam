package au.com.domain.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import au.com.domain.demo.model.Issue;
import au.com.domain.demo.services.IssueServices;
import au.com.domain.demo.services.UserServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    
    // UserServices userServices;
    @Autowired
    IssueServices issueServices;

    // @RequestMapping("/")
    // public String index() {
    // 	System.out.println("Greetings from Spring Boot!"); 
    //     return userServices.findById(1).getUserName();
    // }

	@GetMapping("/issues")
	public List<Issue> retrieveAllIssues() {
		return issueServices.findAll();
	}

}
