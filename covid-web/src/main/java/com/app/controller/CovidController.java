package com.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.CovidAreaDescEntity;
import com.app.entity.CovidCasesAreaEntity;
import com.app.model.CovidCasesArea;
import com.app.repository.covid.CovidCasesRepository;
import com.app.service.covid.CovidService;
import com.app.service.covid.api.CovidMiningAPITotalCases;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidController {

	private final static String GET_LATEST_COVID_FROM_DB = "/covid/get/latest";

	private final static String GET_COVID = "/covid/get";

	private final static String ADD_COVID = "/covid/add";

	private final static String DELETE_COVID = "/covid/delete";

	private final static String GET_HELLO_API = "/covid/hello";

	private final static String GET_LOG_API = "/covid/logging";

	@Autowired
	private CovidService covidService;

	@Autowired
	private CovidCasesRepository covidCasesRepository;

	@Autowired
	CovidMiningAPITotalCases covidMiningAPITotalCases;

	@GetMapping(GET_LATEST_COVID_FROM_DB)
	String getLatest() {
		log.info("getLatest() started");
		String returnString = null;

		try {
			returnString = covidMiningAPITotalCases.getTotalfromDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" getLatest() exception " + e.getMessage());
		}

		log.info(GET_LATEST_COVID_FROM_DB + "  return = {}" + returnString);
		return returnString;
	}

	@GetMapping(GET_COVID)
	List<CovidCasesArea> findAll() {
		log.info("findAll() started");
		List<CovidCasesArea> covidCasesAreas = null;
		try {
			covidCasesAreas = covidService.getCovid();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" findAll() exception " + e.getMessage());
		}

		log.info(GET_COVID + "  return = {}" + covidCasesAreas);
		return covidCasesAreas;
	}

	// TODO: Practical 1 - Complete the API below
	// It should return hello when you hit http://localhost:8081/covid/hello on
	// browser

	@GetMapping(GET_HELLO_API)
	String getHello() throws Exception {
		log.info("getHello() started");

		return "Hello API";
	}

	// TODO: Practical 2 - Capture the error message below from log file
	// It should return some error when you pass a string as parameter to the HTTP
	// get
	// Example, http://localhost:8081/covid/hello?aNumberOnly=string

	@GetMapping(GET_LOG_API)
	String getLogging(@RequestParam String aNumberOnly) throws Exception {
		log.info("getLogging() started, requestParamvalue={}", aNumberOnly);

		if (aNumberOnly != null) {
			Integer.parseInt(aNumberOnly);
		}
		return "you have input =>" + aNumberOnly;
	}

	// TODO: Practical 4
	// Move the logic below under try/catch area to CovidServiceImpl
	@GetMapping(ADD_COVID)
	String addCovid() {
		log.info("addCovid() started");
		String strReturn = null;

		try {

			List<CovidCasesAreaEntity> cases = covidCasesRepository.findAll();
			CovidCasesAreaEntity covidCasesAreaEntity = cases.get(0);
			CovidCasesAreaEntity covidCasesAreaEntityNew = new CovidCasesAreaEntity();

			covidCasesAreaEntityNew.setArea(covidCasesAreaEntity.getArea());
			covidCasesAreaEntityNew.setDate(new Date());

			CovidAreaDescEntity covidAreaDescEntity = new CovidAreaDescEntity();

			covidAreaDescEntity.setDescription("!");

			covidCasesRepository.save(covidCasesAreaEntityNew);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("add() exception " + e.getMessage());
		}

		return strReturn;
	}
}
