package service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.service.covid.CovidService;

import config.ConfigurationUnitTestLoader;
import lombok.extern.slf4j.Slf4j;

// Unit Test Reference 
// https://stackabuse.com/how-to-test-a-spring-boot-application/

//TODO: Practical Advance 
// Research to read from 
// 1. json in a text file
// 2. convert into pojo 

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
@ContextConfiguration(classes=ConfigurationUnitTestLoader.class)

public class CovidServicesTest {

	@Autowired
	private CovidService covidService;

	@Test
	public void testCovidService() {
		assertNotNull(covidService);
	}

}
