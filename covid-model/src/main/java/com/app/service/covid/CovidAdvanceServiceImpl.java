package com.app.service.covid;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.CovidCasesDescEntity;
import com.app.mapper.CovidAreaDescMapper;
import com.app.model.CovidCasesDesc;
import com.app.repository.covid.CovidCasesDescRepository;
import com.app.util.DateTools;

import fr.xebia.extras.selma.Selma;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CovidAdvanceServiceImpl implements CovidAdvanceService {

	@Autowired
	CovidCasesDescRepository covidCasesDescRepository;

	// TODO: Practical Advance
	// Create this method on CovidAdvanceBonusService to simulate the roll back
	@Override
	@Transactional
	public void addCovidRollBack(String desc) {
		log.info("addCovidRoll started");

		CovidCasesDescEntity covidAreaDescEntity = new CovidCasesDescEntity();

		covidAreaDescEntity.setDescription(desc + " " + DateTools.getDefaultDate());

		covidCasesDescRepository.save(covidAreaDescEntity);

		// the exception below cause covidCasesDescRepository.save command above will
		// not be executed and rolled back
		throw new RuntimeException("RollBack in purpose");

	}
	
	// TODO: Practical Advance
	// Create this method on CovidAdvanceBonusService to simulate the roll back
	@Override
	@Transactional
	public CovidCasesDesc addCovidNoRollBack(String desc) {
		log.info("addCovid started");
		
		CovidCasesDesc covidCasesDesc = null;
		
		CovidCasesDescEntity covidAreaDescEntity = new CovidCasesDescEntity();

		covidAreaDescEntity.setDescription(desc + " " + DateTools.getDefaultDate());

		CovidCasesDescEntity savedEntity = covidCasesDescRepository.save(covidAreaDescEntity);

		CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();

		covidCasesDesc = mapper.asResource(savedEntity);
		
		return covidCasesDesc;

	}
}
