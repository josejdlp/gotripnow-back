package com.jose.gotripnowback.service;

import com.jose.gotripnowback.dto.Message;
import com.jose.gotripnowback.dto.api.ApiCapture;
import com.jose.gotripnowback.entity.Capture;
import org.springframework.http.ResponseEntity;

public interface CaptureService {

     ResponseEntity<Message> createCapture(ApiCapture c);

}
