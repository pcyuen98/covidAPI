package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesBonus;

import lombok.extern.slf4j.Slf4j;

//TODO: Practical bonus final
//complete this as Dependencies Injection Service
@Slf4j
public class CovidBonusServiceImpl implements CovidBonusService {

	// hint
	// the method is similar to getCovidDesc() CovidServiceImpl file
	
	@Override
	public
	List<CovidCasesBonus> bonus() throws Exception {
		List<CovidCasesBonus> CovidCasesBonus = null;
		log.info("bonus() started");

		log.info("bonus() ends");
		return CovidCasesBonus;
	}
}
