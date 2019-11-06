package com.example.service;

import java.util.List;

import com.example.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.model.LoginUser;
import com.example.model.Passenger;
import com.example.repository.EmployeeRepository;
import com.example.repository.PassengerRepository;

@Service("passengerService")
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerRepository passengerRepository;

	public Page<Passenger> findAll(Pageable pageable) {
		return passengerRepository.findAll(pageable);
	}

	@Override
	public Passenger getPassengerById(Integer id) {
		return passengerRepository.findOne(id);
	}

	
	@Override
	public boolean savePassenger(Passenger passenger) {
		// TODO Auto-generated method stub
		try {
			passengerRepository.save(passenger);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
