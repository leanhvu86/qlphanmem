package com.example.service;

import java.util.List;

import com.example.model.Employee;
import com.example.model.Passenger;
import com.example.model.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PassengerService {

	Page<Passenger> findAll(Pageable pageable);

	public Passenger getPassengerById(Integer id);

	public boolean savePassenger(Passenger passenger);
}
