package com.jose.gotripnowback.repository;

import com.jose.gotripnowback.entity.Objetive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ObjetiveRepository extends JpaRepository<Objetive,Integer> {

    Optional<Objetive> findByName(String name);

}
