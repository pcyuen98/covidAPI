package test;

import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.entity.CovidCasesDescEntity;
import com.app.repository.covid.CovidCasesDescRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
@ActiveProfiles(profiles = "unitTest")
@PropertySource("classpath:applicationUnitTest.properties")
public class CovidRepoTest {

	@Autowired
	CovidCasesDescRepository covidCasesDescRepository;
	
	@Autowired
	ApplicationContext context;

	@Transactional
	private void saveTest() throws Exception {
		CovidCasesDescEntity covidCasesDescEntity  = new CovidCasesDescEntity();
		covidCasesDescEntity.setId(99l);
		covidCasesDescEntity.setDescription("test.....133333444");
		covidCasesDescRepository.save(covidCasesDescEntity);
	//	throw new Exception("test");
		
	}
	
	@Test
	public void covidCasesDescRepositoryTest() throws Exception {
		CovidCasesDescEntity covidCasesDescEntity  = new CovidCasesDescEntity();
		covidCasesDescEntity.setId(99l);
		covidCasesDescEntity.setDescription("test.....122");
		covidCasesDescRepository.save(covidCasesDescEntity);
		saveTest();
		assertNotNull(covidCasesDescRepository);
	}

}
