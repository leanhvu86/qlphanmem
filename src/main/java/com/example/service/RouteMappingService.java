package com.example.service;

import com.example.model.RouteMapping;
import com.example.model.RouteRail;

import java.util.List;

public interface RouteMappingService {

    public List<RouteMapping> getAllRouteMapping();

    public RouteMapping getRouteMappingById(Integer id);

    public void save(RouteMapping routeMapping);
}
