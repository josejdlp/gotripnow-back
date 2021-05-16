package com.jose.gotripnowback.service;

import com.jose.gotripnowback.dto.Message;
import com.jose.gotripnowback.entity.Objetive;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ObjetiveService {

    List<Objetive> getObjetives();

    ResponseEntity<Message> createObjetive(Objetive objetive);

}
