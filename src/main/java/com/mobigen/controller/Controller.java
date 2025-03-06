package com.mobigen.controller;


import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    
    @GetMapping("/hi")
    public String getMethodName() {
        return "Hello World";
    }
}
