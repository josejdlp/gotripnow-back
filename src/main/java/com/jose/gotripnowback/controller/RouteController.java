package com.jose.gotripnowback.controller;

import com.jose.gotripnowback.dto.AssociateObjetives_input;
import com.jose.gotripnowback.dto.Message;
import com.jose.gotripnowback.dto.api.ApiRoute;
import com.jose.gotripnowback.dto.api.ApiRouteNew;
import com.jose.gotripnowback.entity.Objetive;
import com.jose.gotripnowback.entity.Producto;
import com.jose.gotripnowback.entity.Route;
import com.jose.gotripnowback.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
@CrossOrigin(origins = "*")
public class RouteController {

//TODO obtener nick logeado:   UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    @Autowired
    RouteService routeService;

    @GetMapping()
    public ResponseEntity<List<Route>> list(){
        List<Route> list = routeService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("{id_route}")
    public ResponseEntity<Route> routePerId(@PathVariable("id_route") Integer idRoute){
        Route route = routeService.getRouteById(idRoute);
        return new ResponseEntity(route, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createRoute(@RequestBody ApiRouteNew route){
         routeService.createRoute(route);
         return new ResponseEntity(route, HttpStatus.OK);
    }

   /* @PutMapping("{id_route}")
    public ResponseEntity<Message> associateObjetives(@PathVariable("id_route") Integer idRoute,
                                                      @RequestBody AssociateObjetives_input idsObjetives){

        return routeService.associateObjetives(idRoute,idsObjetives);
    }*/

}
