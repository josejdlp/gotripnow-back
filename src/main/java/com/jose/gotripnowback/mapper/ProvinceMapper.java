package com.jose.gotripnowback.mapper;

import com.jose.gotripnowback.dto.api.ApiProvince;
import com.jose.gotripnowback.dto.api.ApiRoute;
import com.jose.gotripnowback.entity.Province;

import java.util.List;
import java.util.stream.Collectors;

public class ProvinceMapper {

    public static List<ApiProvince> toApiProvince(List<Province> provinces){
        return provinces.stream().map(p -> ApiProvince.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .routes(p.getRoutes().stream().map(route -> ApiRoute.builder()
                        .id(route.getId())
                        .name(route.getName())
                        .build()).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
    }

}
