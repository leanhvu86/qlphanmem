package com.example.service;

import com.example.model.Employee;
import com.example.model.RouteRail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RouteRailService {

    public List<RouteRail> getAllRouteRail();

    public RouteRail getRouteRailById(Integer id);

    Page<RouteRail> findAll(Pageable pageable);
}
