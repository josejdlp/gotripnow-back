package com.jose.gotripnowback.service;

import com.jose.gotripnowback.dto.api.ApiRouteProfile;

import java.util.List;

public interface UserService {
   public List<ApiRouteProfile> getCapturesFromUser();
}
