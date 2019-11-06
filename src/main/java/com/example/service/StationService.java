package com.example.service;

import com.example.model.Employee;
import com.example.model.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StationService {

	public Iterable<Station> getAllStations();

	public Station getStationById(Integer id);

	Page<Station> findAll(Pageable pageable);

	List<Station> findByRoundId(Integer id);

	Station findByName(String name);

	boolean saveStation(Station station);

	void deleteStation(Integer id);
}
