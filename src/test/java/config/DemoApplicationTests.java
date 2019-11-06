package config;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.BeforeClass;

import com.example.model.Employee;
import com.example.model.RouteRail;
import com.example.model.Station;
import com.example.service.EmployeeService;
import com.example.service.RouteRailService;

import junit.framework.Assert;
import static org.junit.Assert.assertFalse;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	RouteRailService routeRailService;
    @Autowired
    private EmployeeService employeeService;
    private Validator validator;

    @BeforeClass
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
	@Test
	public void contextLoads() {
		List<RouteRail> lstRouteRail = routeRailService.getAllRouteRail();
		
		System.out.println(lstRouteRail.size());
	}

	@Test
	public void saveEmployee() {
		Employee employee= new Employee();
		employee.setActive(true);
		employee.setDescription("fdasfafdsafasfd");
		employee.setEmail("leanhvu86@gmail.com");
		employee.setLastName("Vu3333aa");
		employee.setName("Le Anh3333aaa");
		employee.setPhone("094510926233333");
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertFalse(violations.isEmpty());
	}
}
