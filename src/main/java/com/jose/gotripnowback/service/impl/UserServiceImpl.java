package com.jose.gotripnowback.service.impl;

import com.jose.gotripnowback.dto.Message;
import com.jose.gotripnowback.dto.api.ApiObjetive;
import com.jose.gotripnowback.dto.api.ApiRoute;
import com.jose.gotripnowback.dto.api.ApiRouteProfile;
import com.jose.gotripnowback.entity.Capture;
import com.jose.gotripnowback.security.entity.Usuario;
import com.jose.gotripnowback.security.repository.UsuarioRepository;
import com.jose.gotripnowback.service.UserController;
import com.jose.gotripnowback.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserControllerImpl implements UserController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    ResponseEntity<Message> getCapturesFromUser(){
        Usuario u=usuarioRepository.findByNombreUsuario( UserUtils.getUsernameLogged()).get();
        Map<Integer, ApiRouteProfile> routes=new HashMap<>();

        for (Capture capture:u.getCaptures()) {
            if(routes.containsKey(capture.getRoute().getId())){
                routes.put(capture.getRoute().getId()
                            , ApiRouteProfile.builder()
                                .id(capture.getRoute().getId())
                                .name(capture.getRoute().getName())
                                .build());
            }
        }

        //bucle del mapa de rutas que esta siguiendo el usuario para setear los objetivos si cada uno es captured o no:
        

        return null;
    }

}
