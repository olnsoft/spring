package com.kipper.first_spring_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kipper.first_spring_app.domain.User;
import com.kipper.first_spring_app.service.HelloWorldService;

@RestController
@RequestMapping ("/hello-world")
public class HelloWorldController {

    @Autowired 
    private HelloWorldService helloWorldService;
    
    @GetMapping 
    public String helloWorld() {
        return helloWorldService.helloWorld("Orma");
    }

    @PostMapping("/{id}")
    public String helloWorld(@PathVariable("id") String id, @RequestBody User body) {
        return helloWorldService.helloWorld(body.getName().concat(" - ID: ").concat(id));
    }

}
