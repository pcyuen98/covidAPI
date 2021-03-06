package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.covid.CovidService;
import com.app.model.CovidCasesArea;

@RestController

public class CovidController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CovidController.class);

	private final static String GET_COVID = "/covid/get";
	@Autowired
	private CovidService covidService;

	// Find
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

}
