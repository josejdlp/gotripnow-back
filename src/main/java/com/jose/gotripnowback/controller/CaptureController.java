package com.jose.gotripnowback.controller;

import com.jose.gotripnowback.dto.Message;
import com.jose.gotripnowback.dto.api.ApiCapture;
import com.jose.gotripnowback.entity.Capture;
import com.jose.gotripnowback.service.CaptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/capture")
@CrossOrigin(origins = "*")
public class CaptureController {

    @Autowired
    CaptureService captureService;

    @PostMapping
    ResponseEntity<Message> createObjetive(@RequestBody ApiCapture capture){
        return captureService.createCapture(capture);
    }

}
