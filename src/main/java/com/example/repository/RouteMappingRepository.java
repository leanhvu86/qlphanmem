package com.example.repository;

import com.example.model.RouteMapping;
import com.example.model.RouteRail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("routeMappingRepository")
public interface RouteMappingRepository extends JpaRepository<RouteMapping, Integer> {

}
