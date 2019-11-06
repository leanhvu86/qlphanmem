package com.example.service;

import com.example.model.Employee;
import com.example.model.Station;
import com.example.repository.EmployeeRepository;
import com.example.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Service("stationService")
public class StationServiceImpl implements StationService {

	@Autowired
	private StationRepository stationRepository;
	private Validator validator;

	@Override
	public Iterable<Station> getAllStations() {
		return stationRepository.findAll();
	}

	@Override
	public Station getStationById(Integer id) {
		return stationRepository.findOne(id);
	}

	public Page<Station> findAll(Pageable pageable) {
		return stationRepository.findAll(pageable);
	}

	public List<Station> findByRoundId(Integer roundId) {
		return stationRepository.findByRouteId(roundId);
	}

	public Station findByName(String name) {
		return stationRepository.findByNameStation(name);
	}

	public boolean saveStation(Station station) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		Set<ConstraintViolation<Station>> violations = validator.validate(station);
		Map<String, String> result = new HashMap<>();
		for (ConstraintViolation<Station> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			// Add JSR-303 errors to BindingResult
			// This allows Spring to display them in view via a FieldError
			result.put(propertyPath, "Invalid " + propertyPath + "(" + message + ")");
		}
		if (result.isEmpty()) {
			stationRepository.save(station);
			return true;
		} else {
			return false;
		}
	}
	
	public void deleteStation(Integer id) {
		stationRepository.delete(id);
	}
}
