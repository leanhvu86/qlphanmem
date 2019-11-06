package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	private Validator validator;

	@Override
	public Iterable<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		return employeeRepository.findOne(id);
	}

	public Page<Employee> findAll(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}

	public boolean saveEmployeee(Employee employee) {
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			validator = factory.getValidator();
			Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
			Map<String, String> result = new HashMap<>();
			for (ConstraintViolation<Employee> violation : violations) {
				String propertyPath = violation.getPropertyPath().toString();
				String message = violation.getMessage();
				// Add JSR-303 errors to BindingResult
				// This allows Spring to display them in view via a FieldError
				result.put(propertyPath, "Invalid " + propertyPath + "(" + message + ")");
			}
			if (result.isEmpty()) {
				employeeRepository.save(employee);
				return true;
			} else {
				return false;
			}
		} catch (DataIntegrityViolationException | ConstraintViolationException e1) {
			e1.printStackTrace();
			return false;
		}

	}

	public boolean deleteEmployee(Integer id) {
		try {
			employeeRepository.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
