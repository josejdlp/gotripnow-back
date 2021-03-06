package com.jose.gotripnowback.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Objetive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String description;

    String urlWiki;

    String latitude;

    String longitude;

    @ManyToMany
    private List<Route> routes;

    @OneToMany
    private List<Capture> captures;
}
