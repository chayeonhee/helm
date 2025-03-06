package com.mobigen.controller;


import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {
    
    @GetMapping("/hi")
    public String getMethodName() {
        return "Hello World";
    }
}
