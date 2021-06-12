package com.jose.gotripnowback.controller;

import com.jose.gotripnowback.dto.Message;
import com.jose.gotripnowback.dto.api.ApiRouteProfile;
import com.jose.gotripnowback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/captures")
    ResponseEntity<ApiRouteProfile> getCapturesFromUser(){
            return new ResponseEntity(userService.getCapturesFromUser(), HttpStatus.OK);

    }

}
