package com.jose.gotripnowback.dto.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ApiProvince {
    private int id;

    private String name;

    private String description;

    private List<ApiRoute> routes;
}
