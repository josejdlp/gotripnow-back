package com.jose.gotripnowback.mapper;

import com.jose.gotripnowback.dto.api.ApiProvince;
import com.jose.gotripnowback.entity.Province;

import java.util.List;
import java.util.stream.Collectors;

public class ProvinceMapper {

    public static List<ApiProvince> toApiProvince(List<Province> provinces){
        return provinces.stream().map(p -> ApiProvince.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .build()).collect(Collectors.toList());
    }

}
