package com.jose.gotripnowback.controller;

import com.jose.gotripnowback.dto.api.ApiProvince;
import com.jose.gotripnowback.entity.Objetive;
import com.jose.gotripnowback.entity.Province;
import com.jose.gotripnowback.mapper.ProvinceMapper;
import com.jose.gotripnowback.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provinces")
@CrossOrigin(origins = "*")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping()
    ResponseEntity<List<ApiProvince>> getProvinces(){
        return new ResponseEntity(ProvinceMapper.toApiProvince(provinceService.getProvinces()), HttpStatus.OK);
    }
}
