package com.mobigen.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/",method = RequestMethod.GET)
public class Controller {
    @Value("${MESSAGE}")
    private String uiMessage;

    @GetMapping("/hi")
    public String getMethodName() {
        return uiMessage;
    }
}
