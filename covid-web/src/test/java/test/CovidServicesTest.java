package test;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.service.covid.CovidService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
@SpringBootApplication(scanBasePackages = {"com"})
public class CovidServicesTest {

	@Autowired
	private CovidService covidService;

	@Autowired
	ApplicationContext context;
	
	@Test
	public void contextLoads() {
		
		String[] beanNames = context.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {

			if (beanName.indexOf("covid") > -1) {
				log.info("configured service name:={}", beanName);
			}
		}
	}

	@Test
	public void testCovidService() {
		assertNotNull(covidService);
	}

}
