package com.mobigen.controller;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api",method = RequestMethod.GET)
public class Controller {
    
    @GetMapping("/hi")
    public String getMethodName() {
        return "Hello World!!!";
    }
}
