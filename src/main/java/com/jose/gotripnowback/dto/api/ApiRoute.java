package com.jose.gotripnowback.dto.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApiRoute {
    private int id;
    private String name;
}
