package com.jose.gotripnowback.service.impl;

import com.jose.gotripnowback.dto.AssociateObjetives_input;
import com.jose.gotripnowback.dto.Message;
import com.jose.gotripnowback.entity.Objetive;
import com.jose.gotripnowback.entity.Route;
import com.jose.gotripnowback.repository.ObjetiveRepository;
import com.jose.gotripnowback.repository.RouteRepository;
import com.jose.gotripnowback.service.RouteService;
import com.jose.gotripnowback.util.Constants;
import javassist.NotFoundException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    ObjetiveRepository objetiveRepository;

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
        if(routeRepository.findByName(route.getName()).isPresent()){
            return new ResponseEntity<>(new Message(Constants.ROUTE_EXIST), HttpStatus.BAD_REQUEST);
        }
        if(route.getName().isBlank()){
            return new ResponseEntity<>(new Message(Constants.ERROR_NAME), HttpStatus.BAD_REQUEST);
        }

        routeRepository.save(route);


        return new ResponseEntity<>(new Message(Constants.ROUTE_CREATED),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> associateObjetives(Integer idRoute, AssociateObjetives_input associateObjetives_input) {

        Optional<Route> routeToModify=routeRepository.findById(idRoute);
        Route route;
        List<Objetive> objetives=new ArrayList<>();
        if(routeToModify.isPresent()){
            route=routeToModify.get();

            try{

                for (Integer idObjetive: associateObjetives_input.getIdsObjetives()) {
                    Objetive objetive=objetiveRepository.findById(idObjetive).orElseThrow(()-> new NotFoundException(Constants.OBJETIVE_NOT_FOUND));
                    objetives.add(objetive);
                }
                route.getObjetives().addAll(objetives);
                routeRepository.save(route);

            }catch (JDBCConnectionException e){
                System.out.println("Error en BBDD"+e);
                return new ResponseEntity<>(new Message(Constants.ERROR_BBDD),HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (NotFoundException e){
                return new ResponseEntity<>(new Message(Constants.OBJETIVE_NOT_FOUND),HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new Message(Constants.OBJETIVES_ASSOCIATED),HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(new Message(Constants.ROUTE_NOT_FOUND),HttpStatus.NOT_FOUND);
        }

    }
}
