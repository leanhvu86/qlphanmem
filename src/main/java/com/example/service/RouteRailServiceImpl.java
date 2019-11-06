package com.example.service;

import com.example.model.Employee;
import com.example.model.RouteRail;
import com.example.model.Station;
import com.example.repository.RouteRailRepository;
import com.example.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("routeRailService")
public class RouteRailServiceImpl implements RouteRailService {


	@Autowired
	private RouteRailRepository routeRailRepository;

	@Override
	public List<RouteRail> getAllRouteRail() {
		return routeRailRepository.findAllByRouteIdIsNotNull();
	}

	@Override
	public RouteRail getRouteRailById(Integer id) {
		return routeRailRepository.findOne(id);
	}

	public Page<RouteRail> findAll(Pageable pageable){return  routeRailRepository.findAll(pageable);}
}
