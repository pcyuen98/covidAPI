package com.app.mining.controller;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyCovidMiningController {

	private final static String MINING_MY_COVID = "/covid/mining/my";

	// TODO: Practical 5, move the required logic from covid-web project to here
	
	// CovidMiningApiTotalCasesImpl need to be fixed too. Refer to the file TODO remarks
	@GetMapping(MINING_MY_COVID)
	String mining() {
		log.info("mining() started");
	
		return null;
	}
	
}
