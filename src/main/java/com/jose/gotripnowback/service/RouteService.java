package com.jose.gotripnowback.service;

import com.jose.gotripnowback.dto.AssociateObjetives_input;
import com.jose.gotripnowback.dto.Message;
import com.jose.gotripnowback.dto.api.ApiRoute;
import com.jose.gotripnowback.dto.api.ApiRouteNew;
import com.jose.gotripnowback.entity.Objetive;
import com.jose.gotripnowback.entity.Route;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RouteService {
    List<Route> list();

    Route getRouteById(Integer id);

    ResponseEntity<Message> createRoute(ApiRouteNew route);

    ResponseEntity<Message> associateObjetives(Integer idRoute, AssociateObjetives_input idsObjetives);
}
