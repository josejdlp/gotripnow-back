package com.jose.gotripnowback.controller;

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
    public ResponseEntity<?> createRoute(@RequestBody Route route){
        return routeService.createRoute(route);
    }
}
