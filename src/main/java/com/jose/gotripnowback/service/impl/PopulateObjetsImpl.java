package com.jose.gotripnowback.service.impl;

import com.jose.gotripnowback.entity.Objetive;
import com.jose.gotripnowback.entity.Province;
import com.jose.gotripnowback.entity.Route;
import com.jose.gotripnowback.repository.ObjetiveRepository;
import com.jose.gotripnowback.repository.ProductoRepository;
import com.jose.gotripnowback.repository.ProvinceRepository;
import com.jose.gotripnowback.repository.RouteRepository;
import com.jose.gotripnowback.security.entity.Rol;
import com.jose.gotripnowback.security.entity.Usuario;
import com.jose.gotripnowback.security.enums.RolNombre;
import com.jose.gotripnowback.security.repository.RolRepository;
import com.jose.gotripnowback.security.repository.UsuarioRepository;
import com.jose.gotripnowback.security.service.RolService;
import com.jose.gotripnowback.service.PopulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PopulateObjetsImpl implements PopulateService {

    @Autowired
    RolService rolService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ObjetiveRepository objetiveRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void populateObjets() {

        Objetive o1=Objetive.builder()
                .name("Catadral de Jaén")
                .description("La Santa Iglesia Catedral de la Asunción de la Virgen es la catedral de Jaén, sede episcopal de la diócesis de Jaén, en la comunidad autónoma de Andalucía..")
                .latitude("37.76497458000444")
                .longitude("-3.7904960738629248")
                .urlWiki("https://es.wikipedia.org/wiki/Catedral_de_la_Asunci%C3%B3n_de_Ja%C3%A9n")
                .build();
        objetiveRepository.save(o1);

        Objetive o2=Objetive.builder()
                .name("Castillo de Jaén")
                .description("El \"Castillo de Jaén\" es, en realidad, un conjunto de tres castillos o fortalezas que conforman un gran recinto, que ocupa casi toda la alargada cima del Cerro de Santa Catalina.")
                .latitude("37.76827860502292")
                .longitude("-3.8023884297881745")
                .urlWiki("https://es.wikipedia.org/wiki/Castillo_de_Ja%C3%A9n")
                .build();
        objetiveRepository.save(o2);

        Objetive o3=Objetive.builder()
                .name("Baños Árabes")
                .description("Los Baños Árabes de la ciudad de Jaén, conocidos como Baño del Niño (en árabe, Hamman al-Walad) están ubicados en los sótanos del Palacio de Villardompardo.")
                .latitude("37.77107171885877")
                .longitude("-3.7942382918569804")
                .urlWiki("https://es.wikipedia.org/wiki/Ba%C3%B1os_%C3%81rabes_de_Ja%C3%A9n")
                .build();
        objetiveRepository.save(o3);

        Route r1=Route.builder()
                .name("Los 3 monumentos imprescindibles")
                .description("Con esta ruta descubrirás los monumentos que debes descubrir, todos con mucha historia!")
                .objetives(new ArrayList<>(Arrays.asList(o1,o2,o3)))
                .build();
        routeRepository.save(r1);

        List<Route> l1=new ArrayList<>();
        l1.add(r1);
        Province p1= Province.builder()
                .name("Jaén")
                .description("Jaén es una provincia española situada al noreste de la comunidad autónoma de Andalucía, y al sur de la península ibérica.")
                .routes(l1)
                .build();
        provinceRepository.save(p1);


        //Roles
        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        rolRepository.save(rolAdmin);
        rolRepository.save(rolUser);

        //Usuarios
        Set<Rol> rolesJose = new HashSet<>();
        rolesJose.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        Usuario jose=new Usuario();
        jose.setNombre("Jose Jimenez");
        jose.setEmail("josejdlp@gmail.com");
        jose.setNombreUsuario("jose");
        jose.setPassword(passwordEncoder.encode("jose"));
        jose.setRoles(rolesJose);
        usuarioRepository.save(jose);

        Set<Rol> rolesAdmin = new HashSet<>();
        rolesAdmin.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        Usuario admin=new Usuario();
        admin.setNombre("Señor Admin");
        admin.setEmail("admin@gmail.com");
        admin.setNombreUsuario("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(rolesAdmin);
        usuarioRepository.save(admin);

        Province granada= Province.builder()
                .name("Granada")
                .description("Granada es una ciudad y municipio español, capital de la provincia homónima, en la comunidad autónoma de Andalucía.")
                .build();

        Province cordoba= Province.builder()
                .name("Córdoba")
                .description("Córdoba es una ciudad y municipio español en Andalucía, capital de la provincia homónima, situada en una depresión a orillas del Guadalquivir y al pie de Sierra Morena")
                .build();

        provinceRepository.save(granada);
        provinceRepository.save(cordoba);
    }
}
