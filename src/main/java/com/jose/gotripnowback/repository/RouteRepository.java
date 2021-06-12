package com.jose.gotripnowback.repository;

import com.jose.gotripnowback.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    Optional<Route> findById(Integer id);

    Optional<Route> findByName(String name);
}
