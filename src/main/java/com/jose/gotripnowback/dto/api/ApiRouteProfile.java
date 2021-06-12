package com.jose.gotripnowback.dto.api;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ApiRouteProfile {
        private Integer id;
        private String name;
        private List<ApiObjetiveProfile> objetives;
}
