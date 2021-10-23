package com.jose.gotripnowback.service.impl;

import com.jose.gotripnowback.dto.Message;
import com.jose.gotripnowback.dto.api.ApiCapture;
import com.jose.gotripnowback.entity.Capture;
import com.jose.gotripnowback.entity.Objetive;
import com.jose.gotripnowback.entity.Route;
import com.jose.gotripnowback.repository.CaptureRepository;
import com.jose.gotripnowback.repository.ObjetiveRepository;
import com.jose.gotripnowback.repository.RouteRepository;
import com.jose.gotripnowback.security.entity.Usuario;
import com.jose.gotripnowback.security.repository.UsuarioRepository;
import com.jose.gotripnowback.service.CaptureService;
import com.jose.gotripnowback.util.Constants;
import com.jose.gotripnowback.util.DateUtils;
import com.jose.gotripnowback.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CaptureServiceImpl implements CaptureService {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    ObjetiveRepository objetiveRepository;

    @Autowired
    CaptureRepository captureRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<Message> createCapture(ApiCapture c) {
        log.info("Creando captura: "+c);
        try{

            Route route=routeRepository.findById(Integer.valueOf(c.getIdRoute())).orElse(null);
            Objetive objetive=objetiveRepository.findById(Integer.valueOf(c.getIdObjetive())).orElse(null);
            if(objetive==null || route==null){
                return new ResponseEntity<>(new Message(Constants.ERROR_CAPTURE), HttpStatus.BAD_REQUEST);
            }
            //Comprobacion de que no esta capturado previamente

            Optional<Capture> captureExist=captureRepository.findByRouteIdAndObjetiveId(route.getId(),objetive.getId());
            if( ! captureExist.isPresent()){
                Capture capture=Capture.builder()
                        .date(DateUtils.getCurrentDate())
                        .objetive(objetive)
                        .route(route)
                        .build();
                captureRepository.save(capture);

                Usuario usuario=usuarioRepository.findByNombreUsuario(UserUtils.getUsernameLogged()).orElse(null);
                if (usuario == null) {
                    return new ResponseEntity<>(new Message(Constants.ERROR_CAPTURE), HttpStatus.BAD_REQUEST);
                }
                usuario.getCaptures().add(capture);
                usuarioRepository.save(usuario);
            }


        }catch (Exception e){
            log.error("Error al crear la captura");
            return new ResponseEntity<>(new Message(Constants.ERROR_CAPTURE), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Message(Constants.OK_CAPTURE), HttpStatus.OK);
    }
}
