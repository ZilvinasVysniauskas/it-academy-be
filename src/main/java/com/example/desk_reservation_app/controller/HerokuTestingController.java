package com.example.desk_reservation_app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/test")
public class HerokuTestingController {
    @GetMapping
    @CrossOrigin
    public String  testHerokuServer() {
        return "asdasfas";
    }
}
