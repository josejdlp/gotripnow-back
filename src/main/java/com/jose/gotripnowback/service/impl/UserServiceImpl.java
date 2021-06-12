package com.jose.gotripnowback.service.impl;

import com.jose.gotripnowback.dto.api.ApiObjetiveProfile;
import com.jose.gotripnowback.dto.api.ApiRouteProfile;
import com.jose.gotripnowback.entity.Capture;
import com.jose.gotripnowback.entity.Route;
import com.jose.gotripnowback.repository.RouteRepository;
import com.jose.gotripnowback.security.entity.Usuario;
import com.jose.gotripnowback.security.repository.UsuarioRepository;
import com.jose.gotripnowback.service.UserService;
import com.jose.gotripnowback.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RouteRepository routeRepository;


    @Override
    public List<ApiRouteProfile> getCapturesFromUser() {

            Usuario u=usuarioRepository.findByNombreUsuario( UserUtils.getUsernameLogged()).get();
            Map<Integer, ApiRouteProfile> routesInProgres=new HashMap<>();
            Set<Integer> idsObjetivesCaptured=new HashSet<>();
            List<ApiRouteProfile> result=new ArrayList<>();

            for (Capture capture:u.getCaptures()) {
                idsObjetivesCaptured.add(capture.getObjetive().getId());
                if( ! routesInProgres.containsKey(capture.getRoute().getId())){
                    routesInProgres.put(capture.getRoute().getId()
                            , ApiRouteProfile.builder()
                                    .id(capture.getRoute().getId())
                                    .name(capture.getRoute().getName())
                                    .objetives(new ArrayList<>(Arrays.asList( ApiObjetiveProfile.builder()
                                            .id(capture.getObjetive().getId())
                                            .name(capture.getObjetive().getName())
                                            .captured(true)
                                            .date(capture.getDate())
                                            .build()) ))
                                    .build());
                }else{
                    //sobre esa misma ruta incrementar add objetivo con captured a true
                    routesInProgres.get(capture.getRoute().getId()).getObjetives().add(ApiObjetiveProfile.builder()
                            .id(capture.getObjetive().getId())
                            .name(capture.getObjetive().getName())
                            .captured(true)
                            .date(capture.getDate())
                            .build());
                }
            }

            //bucle del mapa de rutas que esta siguiendo el usuario para setear los objetivos que le faltan por completar en la ruta
            routesInProgres.forEach((k,v)-> {
                //Carga el objeto Ruta que contiene la lista de objetivos completa
                Route route=routeRepository.findById(k).get();

                route.getObjetives().forEach(objetive -> {
                    //la ruta capturada con los objetivos capturados
                    //ahora hay que buscar si a esa ruta capturada le faltan objetivos y añadirlos con
                    //el captured = false
                    if(!idsObjetivesCaptured.contains(objetive.getId())){
                        routesInProgres.get(k).getObjetives().add(ApiObjetiveProfile.builder()
                                .id(objetive.getId())
                                .name(objetive.getName())
                                .captured(false)
                                .date("-")
                                .build()); //este objetivo no tiene fecha de captura, porque no esta efectuada
                    }


                });
                result.add(routesInProgres.get(k));
            });


            return result;
        }

}
