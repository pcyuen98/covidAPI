package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;
import com.app.service.covid.CovidAdvanceService;
import com.app.service.covid.CovidService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RollBackControllerTest {

	private final static String ADD_COVID_ROLL_BACK = "/covid/rollback";

	@Autowired
	private CovidService covidService;

	@Autowired
	private CovidAdvanceService covidAdvanceService;

	// TODO: Practical Advance
	// Create this method on CovidAdvanceBonusService to simulate the roll back

	@GetMapping(ADD_COVID_ROLL_BACK)
	CovidCasesDesc rollBack() throws Exception {
		log.info("findAll() started");
		List<CovidCasesArea> covidCasesAreas = null;
		try {
			covidCasesAreas = covidService.getCovid();

			// the record below must be NOT BE saved into DB
			
			// logging must show below
			// Applying rules to determine whether transaction should rollback on
			// java.lang.RuntimeException: RollBack in purpose
			covidAdvanceService.addCovidRollBack("Roll Back");
		} catch (Exception e) {
			log.error(" findAll() exception " + e.getMessage());
		}

		// the record below must be saved into DB
		CovidCasesDesc covidCasesDesc = covidAdvanceService.addCovidNoRollBack("No Roll Back Data Only Recorded");

		log.info(ADD_COVID_ROLL_BACK + "  return = {}" + covidCasesAreas);
		return covidCasesDesc;
	}

}
