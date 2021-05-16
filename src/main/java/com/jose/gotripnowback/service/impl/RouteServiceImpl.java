package com.jose.gotripnowback.service.impl;

import com.jose.gotripnowback.dto.Message;
import com.jose.gotripnowback.entity.Route;
import com.jose.gotripnowback.repository.RouteRepository;
import com.jose.gotripnowback.service.RouteService;
import com.jose.gotripnowback.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;


    @Override
    public List<Route> list() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(Integer id) {
        return routeRepository.findById(id).isPresent()
                ? routeRepository.findById(id).get()
                : null;
    }

    @Override
    public ResponseEntity<Message> createRoute(Route route) {
        if(routeRepository.findByNombre(route.getNombre()).isPresent()){
            return new ResponseEntity<>(new Message(Constants.ROUTE_EXIST), HttpStatus.BAD_REQUEST);
        }
        if(route.getNombre().isBlank()){
            return new ResponseEntity<>(new Message(Constants.ERROR_NAME), HttpStatus.BAD_REQUEST);
        }

        routeRepository.save(route);


        return new ResponseEntity<>(new Message(Constants.ROUTE_CREATED),HttpStatus.OK);
    }
}
