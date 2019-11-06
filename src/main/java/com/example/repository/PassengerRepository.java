package com.example.repository;

import com.example.model.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Employee;
import com.example.model.Passenger;

@Repository("passengerRepository")
public interface PassengerRepository extends CrudRepository<Passenger, Integer> {
    Page<Passenger> findAll(Pageable pageable);
}
