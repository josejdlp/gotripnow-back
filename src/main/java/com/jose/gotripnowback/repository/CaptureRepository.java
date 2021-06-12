package com.jose.gotripnowback.repository;

import com.jose.gotripnowback.entity.Capture;
import com.jose.gotripnowback.entity.Objetive;
import com.jose.gotripnowback.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaptureRepository extends JpaRepository<Capture,Integer> {

    Optional<Capture> findByRouteIdAndObjetiveId(Integer idRoute, Integer idObjetive);

}
