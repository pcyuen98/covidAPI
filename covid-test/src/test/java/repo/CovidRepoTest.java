package repo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.CovidCasesDescEntity;
import com.app.repository.covid.CovidCasesDescRepository;
import com.app.service.covid.CovidService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
//@ContextConfiguration(classes = ConfigurationUnitTestLoader.class)
@SpringBootApplication(scanBasePackages = { "com","com.app.service.covid","com.app.repository.covid.CovidCasesDescRepository" })

public class CovidRepoTest {

	@Autowired
	CovidCasesDescRepository covidCasesDescRepository;

	@Autowired
	ApplicationContext context;
	
	@Autowired
	private CovidService covidService;

	 @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class })
	private void saveTest() throws Exception {
		 
		 try {
			 covidService.addCovid("roll");
		 }
		 catch(Exception e)
		 {
			 log.info("dummy catch" + e);
		 }
		 covidService.addCovid("No roll back");

	}

	@Test
	public void covidCasesDescRepositoryTest() throws Exception {

		assertNotNull(covidCasesDescRepository);
	}

}
