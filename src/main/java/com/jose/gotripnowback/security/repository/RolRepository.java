package com.jose.gotripnowback.security.repository;

import com.jose.gotripnowback.security.entity.Rol;
import com.jose.gotripnowback.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
