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

        Objetive o4=Objetive.builder()
                .name("Mezquita")
                .description("La mezquita-catedral de Córdoba, Santa María Madre de Dios» o «Gran mezquita de Córdoba», actualmente conocida como la Catedral de la Asunción de Nuestra Señora de forma eclesiástica, es un edificio de la ciudad de Córdoba, España.")
                .latitude("37.87896064444783")
                .longitude("-4.77936812453781")
                .urlWiki("https://es.wikipedia.org/wiki/Mezquita-catedral_de_C%C3%B3rdoba")
                .build();
        objetiveRepository.save(o4);

        Objetive o5=Objetive.builder()
                .name("Medina-Azahara")
                .description("La ciudad de Córdoba cuenta con 4 declaraciones de Patrimonio de la Humanidad, Medina-Azahara es una de ellas. Se trata de un yacimiento arqueológico, situado a 8 kilómetros del centro de la ciudad, en el que pueden verse las ruinas.")
                .latitude("37.88665987054314")
                .longitude("-4.868148465306517")
                .urlWiki("https://es.wikipedia.org/wiki/Medina_Azahara")
                .build();
        objetiveRepository.save(o5);

        Objetive o6=Objetive.builder()
                .name("Iglesia de San Idelfonso")
                .description("Fue erigida en 1248 en el arrabal San Ildefonso, creado tras la ampliación del recinto amurallado de la ciudad.")
                .latitude("37.767313661130785")
                .longitude("-3.7865038846559305")
                .urlWiki("https://es.wikipedia.org/wiki/Bas%C3%ADlica_de_San_Ildefonso_(Ja%C3%A9n)")
                .build();
        objetiveRepository.save(o6);

        Objetive o7=Objetive.builder()
                .name("Iglesia El Salvador")
                .description("Fue erigida en el año 1985 por el obispo Miguel Peinado Peinado ante la necesidad de llevar nuevos servicios religiosos a los barrios nuevos de la ciudad.")
                .latitude("37.77884143291229")
                .longitude("-3.7929520687847664")
                .urlWiki("https://es.wikipedia.org/wiki/Iglesia_del_Salvador_(Ja%C3%A9n)")
                .build();
        objetiveRepository.save(o7);

        Route r1=Route.builder()
                .name("Los 3 monumentos imprescindibles")
                .description("Con esta ruta descubrirás los monumentos que debes descubrir, todos con mucha historia!")
                .objetives(new ArrayList<>(Arrays.asList(o1,o2,o3)))
                .build();
        routeRepository.save(r1);

        Route r2=Route.builder()
                .name("2 monumentos que no te puedes perder")
                .description("Si visitas Córdoba debes visitar estos 2 monumentos con mucha historia.")
                .objetives(new ArrayList<>(Arrays.asList(o4,o5)))
                .build();
        routeRepository.save(r2);

        Route r3=Route.builder()
                .name("Iglesias imprescindibles de Jaén")
                .description("Iglesias católicas que no puedes perderte.")
                .objetives(new ArrayList<>(Arrays.asList(o6,o7)))
                .build();
        routeRepository.save(r3);

        List<Route> l2=new ArrayList<>();
        l2.add(r2);

        List<Route> l1=new ArrayList<>();
        l1.add(r1);
        l1.add(r3);
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
                .routes(l2)
                .build();

        provinceRepository.save(granada);
        provinceRepository.save(cordoba);
    }
}
