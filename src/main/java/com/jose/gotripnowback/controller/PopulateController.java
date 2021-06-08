package com.jose.gotripnowback.controller;

import com.jose.gotripnowback.service.PopulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/populate")
public class PopulateController {

    @Autowired
    PopulateService populateService;

    @GetMapping
    public ResponseEntity<String> pupulate(){
        populateService.populateObjets();
        return new ResponseEntity<>("Populate Objets OK!", HttpStatus.OK);
    }

}
