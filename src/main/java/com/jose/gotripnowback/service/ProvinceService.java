package com.jose.gotripnowback.service;

import com.jose.gotripnowback.entity.Province;
import com.jose.gotripnowback.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    public List<Province> getProvinces(){
       return  provinceRepository.findAll();
    }

}
