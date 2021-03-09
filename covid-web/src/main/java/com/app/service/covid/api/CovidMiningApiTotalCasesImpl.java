package com.app.service.covid.api;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.mapper.CovidCasesAreaMapper;
import com.app.repository.covid.CovidCasesRepository;
import com.app.entity.CovidCasesAreaEntity;
import com.app.model.CovidCasesArea;
import com.app.model.api.Covid19ApiModel;
import com.app.util.DateTools;
import com.app.util.ResffulServices;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import fr.xebia.extras.selma.Selma;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CovidMiningApiTotalCasesImpl implements CovidMiningAPITotalCases {

	private final static String URL = "https://api.covid19api.com/total/country/malaysia/status/confirmed?from=";

	private final static String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	@Autowired
	CovidCasesRepository covidCasesRepository;

	@Override
	public String doMining() throws Exception {

		String defaultTime = "T00:00:00Z";

		String defaultDate = "yyyy-MM-dd";

		Date date1DayBefore = DateTools.minusDate(1);

		Date date3DayBefore = DateTools.minusDate(3);

		String json = getTotalCasesMYFromAPI(defaultDate, defaultTime, date1DayBefore, date3DayBefore);

		List<Covid19ApiModel> covid19ApiModels = convertToObjects(json);

		updateDB(covid19ApiModels);

		int totalCases = getCasesDifferent(covid19ApiModels);

		log.info("convertToObjects Ends. Total Cases = {} ({})", totalCases, date1DayBefore.toString());

		return "Total Cases " + totalCases + " (" + date1DayBefore.toString() + ")";

	}

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

	private void updateDB(List<Covid19ApiModel> covid19ApiModels) throws ParseException {

		List<CovidCasesAreaEntity> covidCasesAreaEntities = covidCasesRepository.listLast5Records();

		for (Covid19ApiModel covid19ApiModel : covid19ApiModels) {
			covid19ApiModel.getDate();

			CovidCasesAreaEntity covidCasesAreaEntity = new CovidCasesAreaEntity();
			covidCasesAreaEntity.setCases(covid19ApiModel.getCases());

			if (!isDuplicate(covidCasesAreaEntities, covid19ApiModel)) {
				log.info("updateDB this record. covid19ApiModel date={}" + covid19ApiModel.getDate());
				Date date = DateTools.convertDate(covid19ApiModel.getDate(), API_DATE_FORMAT);

				covidCasesAreaEntity.setDate(date);
				covidCasesRepository.save(covidCasesAreaEntity);
			}

		}

		log.info("updateDB Ends.");
	}

	private int getCasesDifferent(List<Covid19ApiModel> covid19ApiModels) {
		Covid19ApiModel first = covid19ApiModels.get(0);
		Covid19ApiModel last = covid19ApiModels.get(1);

		log.info("first cases ={}, last cases= {} ", first.getCases(), last.getCases());

		int totalCases = last.getCases() - first.getCases();

		return totalCases;

	}

	private List<Covid19ApiModel> convertToObjects(String json)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, Covid19ApiModel.class);

		List<Covid19ApiModel> cases = mapper.readValue(json, javaType);

		log.info("convertToObjects ends. cases  = {} ", cases.size());

		return cases;

	}

	private String getTotalCasesMYFromAPI(String defaultDate, String defaultTime, Date date1DayBefore,
			Date date3DayBefore) throws Exception {
		StringBuffer urlBuffer = new StringBuffer();

		String stringDate1DayBefore = DateTools.getDate(defaultDate, date1DayBefore) + defaultTime;
		String stringDate3DayBefore = DateTools.getDate(defaultDate, date3DayBefore) + defaultTime;

		log.info("stringDate1DayBefore = {} ", stringDate1DayBefore);
		log.info("stringDate2DayBefore = {} ", stringDate3DayBefore);

		urlBuffer.append(URL);

		urlBuffer.append(stringDate3DayBefore);
		urlBuffer.append("&to=");
		urlBuffer.append(stringDate1DayBefore);

		log.info("urlBuffer = {} ", urlBuffer.toString());
		String json = ResffulServices.GetServices(urlBuffer.toString());
		log.info("getTotalCasesMY ends. json = {} ", json);

		return json;
	}

	@Override
	public List<CovidCasesArea> getLast5RecordsMY() throws Exception {
		// TODO Auto-generated method stub
		List<CovidCasesAreaEntity> casesEntities = covidCasesRepository.listLast5Records();

		CovidCasesAreaMapper mapper = Selma.builder(CovidCasesAreaMapper.class).build();

		List<CovidCasesArea> casesPojos = new ArrayList<CovidCasesArea>();
		for (CovidCasesAreaEntity covidCasesAreaEntity : casesEntities) {
			CovidCasesArea covidCasesArea = mapper.asResource(covidCasesAreaEntity);
			casesPojos.add(covidCasesArea);
		}

		log.info("getLast5RecordsMY ends.  cases = {} ", casesPojos);
		return casesPojos;
	}

	@Override
	public String getTotalfromDB() throws Exception {
		log.info("getTotalfromDB starts. ");
		List<CovidCasesAreaEntity> casesEntities = covidCasesRepository.listLast2Records();
		log.info("getTotalfromDB casesEntities size ={} ", casesEntities.size());
		
		int totalCases = 0;
		String date = "";
		if (!casesEntities.isEmpty()) {
			List<Covid19ApiModel> covidApiModels = new ArrayList<Covid19ApiModel>();

			CovidCasesAreaEntity covidCasesAreaEntity = casesEntities.get(1);
			log.info("getTotalfromDB Last covidCasesAreaEntity date={}, cases={}", covidCasesAreaEntity.getDate(),
					covidCasesAreaEntity.getCases());

			Covid19ApiModel covid19ApiModel = new Covid19ApiModel();
			covid19ApiModel.setCases(covidCasesAreaEntity.getCases());
			covidApiModels.add(covid19ApiModel);

			covidCasesAreaEntity = casesEntities.get(0);
			log.info("getTotalfromDB covidCasesAreaEntity date={}, cases={}", covidCasesAreaEntity.getDate(),
					covidCasesAreaEntity.getCases());
			date = covidCasesAreaEntity.getDate().toString();
			covid19ApiModel = new Covid19ApiModel();
			covid19ApiModel.setCases(covidCasesAreaEntity.getCases());
			covidApiModels.add(covid19ApiModel);
			totalCases = getCasesDifferent(covidApiModels);
		}

		
		
		log.info("getTotalfromDB ends.  totalCases = {} date={}", totalCases,date);
		return "Total Cases " + totalCases + " (" + date + ")";
	}
}
