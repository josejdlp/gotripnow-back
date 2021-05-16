package com.jose.gotripnowback.service.impl;

import com.jose.gotripnowback.dto.Message;
import com.jose.gotripnowback.entity.Objetive;
import com.jose.gotripnowback.repository.ObjetiveRepository;
import com.jose.gotripnowback.service.ObjetiveService;
import com.jose.gotripnowback.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetiveServiceImpl implements ObjetiveService {

    @Autowired
    ObjetiveRepository objetiveRepository;

    @Override
    public List<Objetive> getObjetives() {
        return objetiveRepository.findAll();
    }

    @Override
    public ResponseEntity createObjetive(Objetive objetive) {
       if(objetiveRepository.findByName(objetive.getName()).isPresent()){
           return new ResponseEntity(new Message(Constants.OBJETIVE_EXIST), HttpStatus.BAD_REQUEST);
       }
       if(objetive.getName()==null || objetive.getName().isEmpty()){
           return new ResponseEntity(new Message(Constants.ERROR_NAME), HttpStatus.BAD_REQUEST);
       }

       objetiveRepository.save(objetive);

       return new ResponseEntity(new Message(Constants.OBJETIVE_CREATED),HttpStatus.OK);
    }

}
