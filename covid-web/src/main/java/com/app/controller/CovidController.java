package com.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.CovidCasesAreaEntity;
import com.app.entity.CovidCasesDescEntity;
import com.app.mapper.CovidAreaDescMapper;
import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;
import com.app.repository.covid.CovidCasesDescRepository;
import com.app.repository.covid.CovidCasesRepository;
import com.app.service.covid.CovidService;
import com.app.service.covid.api.CovidMiningAPITotalCases;

import fr.xebia.extras.selma.Selma;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidController {

	private final static String GET_LATEST_COVID_FROM_DB = "/covid/get/latest";

	private final static String GET_COVID = "/covid/get";

	private final static String GET_COVID_DESC = "/covid/get/desc";

	private final static String ADD_COVID = "/covid/add";

	private final static String DELETE_COVID = "/covid/delete";

	private final static String GET_HELLO_API = "/covid/hello";

	private final static String GET_LOG_API = "/covid/logging";

	private final static String PUT_API = "/covid/put";
	
	private final static String DELETE_COVID_SOAPUI = "/covid/delete/soap";
	
	@Autowired
	private CovidService covidService;

	@Autowired
	private CovidCasesRepository covidCasesRepository;

	@Autowired
	private CovidCasesDescRepository covidCasesDescRepository;

	@Autowired
	CovidMiningAPITotalCases covidMiningAPITotalCases;

	@GetMapping(GET_LATEST_COVID_FROM_DB)
	String getLatest() throws Exception {
		//log.info("getLatest() started");
		String returnString = null;

		try {
			returnString = covidMiningAPITotalCases.getTotalfromDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" getLatest() exception " + e.getMessage());
			throw new com.app.error.ControllerException(GET_LATEST_COVID_FROM_DB, e.getMessage());
		}

		//log.info(GET_LATEST_COVID_FROM_DB + "  return = {}" + returnString);
		return returnString;
	}

	@GetMapping(GET_COVID_DESC)
	List<CovidCasesDesc> findAllDesc() throws Exception {
		log.info("findAll() started");
		List<CovidCasesDesc> covidCasesdescs = null;
		try {
			covidCasesdescs = covidService.getCovidDesc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" findAll() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info(GET_COVID_DESC + "  return = {}" + covidCasesdescs);
		return covidCasesdescs;
	}

	@GetMapping(GET_COVID)
	List<CovidCasesArea> findAll() throws Exception {
		log.info("findAll() started");
		List<CovidCasesArea> covidCasesAreas = null;
		try {
			covidCasesAreas = covidService.getCovid();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" findAll() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info(GET_COVID + "  return = {}" + covidCasesAreas);
		return covidCasesAreas;
	}

	// TODO: Practical 1 - Complete the API below
	// It should return hello when you hit http://localhost:8081/covid/hello on
	// browser

	@GetMapping(GET_HELLO_API)
	String getHello() throws Exception {
		//log.info("getHello() started");

		return "Hello API....";
	}

	// TODO: Practical 2 - Capture the error message below from log file
	// It should return some error when you pass a string as parameter to the HTTP
	// get
	// Example, http://localhost:8081/covid/hello?aNumberOnly=string

	@GetMapping(GET_LOG_API)
	String getLogging(@RequestParam String aNumberOnly) throws RuntimeException {
		log.info("getLogging() started, requestParamvalue={}", aNumberOnly);

		if (aNumberOnly != null) {
			Integer.parseInt(aNumberOnly);
		}
		return "you have input =>" + aNumberOnly;
	}

	// TODO: Practical 4 (Add)
	// Move the logic below under try/catch area to CovidServiceImpl
	// check out the remarks of "TODO: Practical 4 " on CovidServiceImpl
	@GetMapping(ADD_COVID)
	CovidCasesDesc addCovid(@RequestParam(required = true) String desc) throws Exception {
		log.info("addCovid() started={}", desc);

		CovidCasesDesc covidCasesDesc = null;
		try {

			if (desc == null || desc.equals("undefined") || desc.equals(""))  {
				throw new NullPointerException(ADD_COVID + ", desc is null or empty");
			}
			List<CovidCasesAreaEntity> cases = covidCasesRepository.findAll();
			CovidCasesAreaEntity covidCasesAreaEntity = cases.get(0);
			CovidCasesAreaEntity covidCasesAreaEntityNew = new CovidCasesAreaEntity();

			covidCasesAreaEntityNew.setArea(covidCasesAreaEntity.getArea());
			covidCasesAreaEntityNew.setDate(LocalDate.now());

			CovidCasesDescEntity covidAreaDescEntity = new CovidCasesDescEntity();

			covidAreaDescEntity.setDescription(desc);

			CovidCasesDescEntity savedEntity = covidCasesDescRepository.save(covidAreaDescEntity);

			CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();

			covidCasesDesc = mapper.asResource(savedEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("add() exception " + e.getMessage());
			throw new com.app.error.ControllerException(ADD_COVID, e.getMessage());
		}

		return covidCasesDesc;
	}

	// TODO: Practical 4 (Delete)
	// Move the logic below under try/catch area to CovidServiceImpl
	// check out the remarks of "TODO: Practical 4 " on CovidServiceImpl
	@DeleteMapping(DELETE_COVID)
	int deleteCovid(@RequestParam(required = true) long id) throws Exception {
		log.info("deleteCovid() started id={}", id);

		try {

			Optional<CovidCasesDescEntity> entityOptional = covidCasesDescRepository.findById(id);

			log.info("Entity found == " + entityOptional.isPresent());

			if (entityOptional.isPresent()) {
				CovidCasesDescEntity covidAreaDescEntity = entityOptional.get();
				covidCasesDescRepository.delete(covidAreaDescEntity);
				return 1;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("deleteCovid() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		return 0;
	}
	
	// TODO: Angular Practical 7 - Full Stack Application for Covid Put HTTP
	@PutMapping(PUT_API)
	CovidCasesDesc putCovid(@RequestBody CovidCasesDesc covidCasesDesc) throws RuntimeException {
		log.info("putCovid() started, covidCasesDesc={}", covidCasesDesc);

		// complete the implementation below
		
		//log.info("putCovid() ends, covidCasesDescSaved={}", null);
		return null;
	}
	
	// TODO: Performance Practical 2 - Performance and Functional Testing
	@DeleteMapping(DELETE_COVID_SOAPUI)

	int deleteCovidSoap(@RequestParam(required = true) String desc) throws Exception {
		log.info("deleteCovidSoap() started desc={}", desc);
		
		// complete the implementation below
		
		log.info("deleteCovidSoap() ended");
		return 0;
	}
	
	
}
