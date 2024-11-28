package com.nipppon.p2p.web.app.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DummyController{
    @GetMapping("/") 
    public String getMessage(){
    	return "Spring Boot Application running on Tomcat server!!";
    }
}