package com.junit;

import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.example.TestNGWithSpringApplication;
import com.example.model.Employee;
import com.example.service.EmployeeService;
import com.example.service.RouteRailService;

@SpringBootTest(classes = TestNGWithSpringApplication.class)
public class testEmployee extends AbstractTestNGSpringContextTests {

	@Autowired
	RouteRailService routeRailService;
	@Autowired
	private EmployeeService employeeService;
	
	Employee employee;

	@BeforeClass
	public void setUp() {
		
	}

	@BeforeMethod()
	public void initializationObject() {
		employee = new Employee();
	}

	@Test
	@Parameters({ "name", "lastName", "email", "phone", "expected" })
	public void saveEmployee(@Optional() String name, @Optional() String lastName, 
			@Optional() String email,@Optional() String phone, @Optional() String expected) throws ParseException {
		
		employee.setName(name);
		employee.setLastName(lastName);
		employee.setEmail(email);
		employee.setPhone(phone);
		employee.setDescription("Tốt");
		employee.setActive(true);
		
		boolean expectedResult;
		if(expected.equalsIgnoreCase("false")) {
			expectedResult=false;
		}else {
			expectedResult=true;
		}
		
		boolean check = employeeService.saveEmployeee(employee);
		assertEquals(check, expectedResult);
	}

	@AfterMethod
	public void tearDownObject() {
		employeeService.deleteEmployee(employee.getId());
	}
}
