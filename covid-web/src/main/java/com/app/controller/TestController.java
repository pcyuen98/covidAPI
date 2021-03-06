package com.app.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.repository.covid.CovidCasesRepository;
import com.app.entity.AreaEntity;
import com.app.entity.CountryEntity;
import com.app.entity.CovidAreaDescEntity;
import com.app.entity.CovidCasesAreaEntity;
import com.app.entity.StateEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController

public class TestController {

	@Autowired
	CovidCasesRepository covidCasesRepository;

	private final static String TEST = "/test";

	private final static String ADD = "/test/add";

	private final static String ADD2 = "/test/add2";

	@GetMapping(TEST)
	String test() {
		log.info("test() started");
		String strReturn = null;

		try {
			List<CovidCasesAreaEntity> cases = covidCasesRepository.findAll();
			CovidCasesAreaEntity covidCasesAreaEntity = cases.get(0);
			covidCasesRepository.delete(covidCasesAreaEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("mining() exception " + e.getMessage());
		}

		return strReturn;
	}

	@GetMapping(ADD)
	String add() {
		log.info("addd() started");
		String strReturn = null;

		try {

			CovidCasesAreaEntity covidCasesAreaEntity = new CovidCasesAreaEntity();

			CovidAreaDescEntity covidAreaDescEntity = new CovidAreaDescEntity();

			covidAreaDescEntity.setDescription("!");

			Set<CovidAreaDescEntity> set = new HashSet<CovidAreaDescEntity>();
			set.add(covidAreaDescEntity);

			AreaEntity areaEntity = new AreaEntity();
			areaEntity.setId(1l);

			CountryEntity countryEntity = new CountryEntity();
			countryEntity.setId(1l);

			StateEntity stateEntity = new StateEntity();
			stateEntity.setId(1l);
			stateEntity.setCountry(countryEntity);
			areaEntity.setState(stateEntity);
			covidCasesAreaEntity.setArea(areaEntity);
			covidCasesAreaEntity.setDate(new Date());
			covidCasesRepository.save(covidCasesAreaEntity);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("add() exception " + e.getMessage());
		}

		return strReturn;
	}

	@GetMapping(ADD2)
	String add2() {
		log.info("addd() started");
		String strReturn = null;

		try {

			List<CovidCasesAreaEntity> cases = covidCasesRepository.findAll();
			CovidCasesAreaEntity covidCasesAreaEntity = cases.get(0);
			CovidCasesAreaEntity covidCasesAreaEntityNew = new CovidCasesAreaEntity();

			covidCasesAreaEntityNew.setArea(covidCasesAreaEntity.getArea());
			covidCasesAreaEntityNew.setDate(new Date());

			CovidAreaDescEntity covidAreaDescEntity = new CovidAreaDescEntity();

			covidAreaDescEntity.setDescription("!");

			// Set<CovidAreaDescEntity> set = new HashSet<CovidAreaDescEntity>();
			// set.add(covidAreaDescEntity);
			// covidCasesAreaEntityNew.setCovidAreaDesc(set);
			covidCasesRepository.save(covidCasesAreaEntityNew);

			// Set<CovidAreaDescEntity> casesNew =
			// covidCasesAreaEntityNew.getCovidAreaDesc();
			// log.info("new details size=={}" , casesNew.size());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("add() exception " + e.getMessage());
		}

		return strReturn;
	}
}
