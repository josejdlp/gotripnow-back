package com.jose.gotripnowback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hola")
public class ExamppleController {

    @GetMapping
    public ResponseEntity<String> hola(){
        return new ResponseEntity<>("Hola mundo!", HttpStatus.OK);
    }

}
