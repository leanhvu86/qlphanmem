package com.example.service;


import java.util.List;

import com.example.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    public Iterable<Employee> getAllEmployees();

    public Employee getEmployeeById(Integer id);

    Page<Employee> findAll(Pageable pageable);

    public boolean saveEmployeee(Employee employee);

    public boolean deleteEmployee(Integer id);
}
