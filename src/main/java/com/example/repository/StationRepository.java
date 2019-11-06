package com.example.repository;

import com.example.model.Employee;
import com.example.model.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stationRepository")
public interface StationRepository extends CrudRepository<Station, Integer> {
    Page<Station> findAll(Pageable pageable);
    List<Station> findByStationId(Integer id);
    List<Station> findByRouteId(Integer id);
    Station findByNameStation(String name);
}
