package com.jose.gotripnowback.controller;

import com.jose.gotripnowback.dto.Message;
import com.jose.gotripnowback.entity.Objetive;
import com.jose.gotripnowback.service.ObjetiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objetives")
@CrossOrigin(origins = "*")
public class ObjetiveController {

    @Autowired
    ObjetiveService objetiveService;

    @GetMapping()
    ResponseEntity<List<Objetive>> getObjetives(){
        return new ResponseEntity(objetiveService.getObjetives(),HttpStatus.OK);
    }


    @PostMapping()
    ResponseEntity<Message> createObjetive(@RequestBody Objetive objetive){
        return objetiveService.createObjetive(objetive);
    }

}
