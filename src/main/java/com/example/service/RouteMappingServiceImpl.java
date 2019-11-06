package com.example.service;

import com.example.model.RouteMapping;
import com.example.model.RouteRail;
import com.example.repository.RouteMappingRepository;
import com.example.repository.RouteRailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("routeMappingService")
public class RouteMappingServiceImpl implements RouteMappingService {


    @Autowired
    private RouteMappingRepository routeMappingRepository;

    @Override
    public List<RouteMapping> getAllRouteMapping() {
        return routeMappingRepository.findAll();
    }

    @Override
    public RouteMapping getRouteMappingById(Integer id) {
        return routeMappingRepository.findOne(id);
    }

    @Override
    public void save(RouteMapping routeMapping) {
        routeMappingRepository.save(routeMapping);
    }
}
