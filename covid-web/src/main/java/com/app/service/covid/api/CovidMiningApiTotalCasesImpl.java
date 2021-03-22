package com.app.service.covid.api;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.entity.CovidCasesAreaEntity;
import com.app.mapper.CovidCasesAreaMapper;
import com.app.model.CovidCasesArea;
import com.app.model.api.Covid19ApiModel;
import com.app.repository.covid.CovidCasesRepository;
import com.app.util.DateTools;
import com.app.util.ResffulServices;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import fr.xebia.extras.selma.Selma;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CovidMiningApiTotalCasesImpl implements CovidMiningAPITotalCases {

	private final static String URL = "https://api.covid19api.com/total/country/malaysia/status/confirmed?from=";

	private final static String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	@Autowired
	CovidCasesRepository covidCasesRepository;

	private Boolean isDuplicate(List<CovidCasesAreaEntity> covidCasesAreaEntities, Covid19ApiModel covid19ApiModel) {

		log.info("isDuplicate Starts. covid19ApiModel={}", covid19ApiModel);

		for (CovidCasesAreaEntity covidCasesAreaEntity : covidCasesAreaEntities) {

			Format formatter = new SimpleDateFormat(API_DATE_FORMAT);
			String convertedDate = formatter.format(covidCasesAreaEntity.getDate());

			log.info("api date='{}' , entity date='{}'", covid19ApiModel.getDate(), convertedDate);

			if (convertedDate.equals(covid19ApiModel.getDate())) {
				log.info("is matched");
				return true;
			} else {
				log.info("is not matched");
			}

		}
		log.info("isDuplicate Ends. nothing Duplicated here");
		return false;
	}


	private int getCasesDifferent(List<Covid19ApiModel> covid19ApiModels) {
		Covid19ApiModel first = covid19ApiModels.get(0);
		Covid19ApiModel last = covid19ApiModels.get(1);

		//log.info("first cases ={}, last cases= {} ", first.getCases(), last.getCases());

		int totalCases = last.getCases() - first.getCases();

		return totalCases;

	}


	

	@Override
	public List<CovidCasesArea> getLast5RecordsMY() throws Exception {
		// TODO Auto-generated method stub

		List<CovidCasesAreaEntity> casesEntities = covidCasesRepository.listLast5RecordsHQL();
		for (CovidCasesAreaEntity e: casesEntities) {
			log.info("e --->" + e);
		}
		CovidCasesAreaMapper mapper = Selma.builder(CovidCasesAreaMapper.class).build();

		List<CovidCasesArea> casesPojos = new ArrayList<CovidCasesArea>();
		for (CovidCasesAreaEntity covidCasesAreaEntity : casesEntities) {
			CovidCasesArea covidCasesArea = mapper.asResource(covidCasesAreaEntity);
			casesPojos.add(covidCasesArea);
		}

		log.info("getLast5RecordsMY ends.");

		return casesPojos;
	}

	@Override
	public List<CovidCasesArea> getLast5RecordsMYWithSize(int size) throws Exception {
		// TODO Auto-generated method stub

		// TODO: Practical bonus 3:

		Pageable page = PageRequest.of(0, size);
		// List<CovidCasesAreaEntity> list =
		// covidCasesRepository.listLast5RecordsHQL(page);

		// complete the code here as getLast5RecordsMY method
		List<CovidCasesArea> casesPojos = new ArrayList<CovidCasesArea>();

		if (casesPojos.size() == 0) {
			throw new Exception("query return nothing!");
		}
		
		log.info("getLast5RecordsMYWithSize ends.");
		return null;
	}

	@Override
	public String getTotalfromDB() throws Exception {
		//log.info("getTotalfromDB starts. ");
		List<CovidCasesAreaEntity> casesEntities = covidCasesRepository.listLast2Records();
		//log.info("getTotalfromDB casesEntities size ={} ", casesEntities.size());

		int totalCases = 0;
		String date = "";
		if (!casesEntities.isEmpty()) {
			List<Covid19ApiModel> covidApiModels = new ArrayList<Covid19ApiModel>();

			CovidCasesAreaEntity covidCasesAreaEntity = casesEntities.get(1);
			//log.info("getTotalfromDB Last covidCasesAreaEntity date={}, cases={}", covidCasesAreaEntity.getDate(),
			//		covidCasesAreaEntity.getCases());

			Covid19ApiModel covid19ApiModel = new Covid19ApiModel();
			covid19ApiModel.setCases(covidCasesAreaEntity.getCases());
			covidApiModels.add(covid19ApiModel);

			covidCasesAreaEntity = casesEntities.get(0);
			//log.info("getTotalfromDB covidCasesAreaEntity date={}, cases={}", covidCasesAreaEntity.getDate(),
			//		covidCasesAreaEntity.getCases());
			date = covidCasesAreaEntity.getDate().toString();
			covid19ApiModel = new Covid19ApiModel();
			covid19ApiModel.setCases(covidCasesAreaEntity.getCases());
			covidApiModels.add(covid19ApiModel);
			totalCases = getCasesDifferent(covidApiModels);
		}

		//log.info("getTotalfromDB ends.  totalCases = {} date={}", totalCases, date);
		return "Total Cases " + totalCases + " (" + date + ")";
	}
}
