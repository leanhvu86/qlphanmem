package com.example.repository;

import com.example.model.RouteRail;
import com.example.model.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("routeRailRepository")
public interface RouteRailRepository extends CrudRepository<RouteRail, Integer> {

    List<RouteRail> findAllByRouteIdIsNotNull();
    Page<RouteRail> findAll(Pageable pageable);
}
