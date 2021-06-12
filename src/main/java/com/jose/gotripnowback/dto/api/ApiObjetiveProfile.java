package com.jose.gotripnowback.dto.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class ApiObjetiveProfile {
    int id;
    String name;
    boolean captured;
    String date;
}
