package com.junit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.example.DemoApplication;
import com.example.TestNGWithSpringApplication;
import com.example.model.LoginUser;
import com.example.model.News;
import com.example.model.RouteRail;
import com.example.model.Station;
import com.example.service.NewsService;
import com.example.service.StationService;
@SpringBootTest(classes = TestNGWithSpringApplication.class)
public class testInsertStation extends AbstractTestNGSpringContextTests {

	private Station station;

	@Autowired
	private StationService stationService;

	@BeforeClass
	public void setUp() {
		
	}
	@BeforeMethod
	public void initializationObject() {
		station = new Station();
	}

	@Test
	@Parameters({ "nameStation", "addressStation", "personCapacity", "startTime","endTime", "expected" })
	public void saveEmployee(@Optional() String nameStation, @Optional() String addressStation, 
			@Optional() String personCapacity,@Optional() String startTime,@Optional() String endTime,  @Optional() String expected) throws ParseException {
		
		station.setNameStation(nameStation);
		station.setAddressStation(addressStation);
		station.setPersonCapacity(personCapacity);
		station.setStartTime(startTime);
		station.setEndTime(endTime);
		station.setCreateDate("15/10/2019");
		station.setDescription("tốt");
		RouteRail routeRail =new RouteRail();
		routeRail.setRouteId(1);
		station.setRouteId(routeRail);
		
		station.setStatus(1);
		station.setTypeStation(1);
		boolean expectedResult;
		if(expected.equalsIgnoreCase("false")) {
			expectedResult=false;
		}else {
			expectedResult=true;
		}
		
		boolean check = stationService.saveStation(station);
		assertEquals(check, expectedResult);
	}

	@AfterMethod
	public void tearDownObject() {
		stationService.deleteStation(station.getStationId());
	}
}
