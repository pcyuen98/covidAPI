package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CovidCasesArea;
import com.app.service.covid.api.CovidMiningAPITotalCases;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MyCovidController {

	private final static String GET_MY_LAST_5_COVID = "/covid/get5/my";

	private final static String MINING_MY_COVID = "/covid/mining/my";

	@Autowired
	CovidMiningAPITotalCases covidMiningAPITotalCases;

	@GetMapping(MINING_MY_COVID)
	String mining() throws Exception {
		log.info("mining() started");
		String strReturn = null;

		try {
			covidMiningAPITotalCases.doMining();
			strReturn = covidMiningAPITotalCases.getTotalfromDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("mining() exception " + e.getMessage());
			throw new Exception(e);
		}

		log.info(MINING_MY_COVID + " return = {}" + strReturn);
		return strReturn;
	}

	@GetMapping(GET_MY_LAST_5_COVID)
	List<CovidCasesArea> getLast5Records() throws Exception {
		log.info("getLast5Records() started");

		log.info(
				"getLast5Records() ends. It supposes to return last 5 records from listLast5Records(). (CovidCasesRepository)");
		return covidMiningAPITotalCases.getLast5RecordsMY();
	}
}
