package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.app.config.GlobalConstants;
import com.app.model.api.Covid19ApiModel;
import com.app.util.DateTools;
import com.app.util.ResffulServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

@Ignore
class Covid19ApiTest {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DateTools.class);

	@Test
	public void getHello() throws Exception {

		TestRestTemplate testRestTemplate = new TestRestTemplate();

		ResponseEntity<String> response = testRestTemplate
				.getForEntity(new URL(GlobalConstants.COVID_API_URL).toString(), String.class);
		assertNotNull(response.getBody());
	}

	@Ignore
	@Test
	void testURL() throws Exception {
		StringBuffer urlBuffer = new StringBuffer();

		String defaultTime = "T00:00:00Z";

		String defaultDate = "yyyy-MM-dd";

		Date date1DayBefore = DateTools.minusDate(1);

		Date date3DayBefore = DateTools.minusDate(3);

		String stringDate1DayBefore = DateTools.getDate(defaultDate, date1DayBefore) + defaultTime;
		String stringDate3DayBefore = DateTools.getDate(defaultDate, date3DayBefore) + defaultTime;

		log.info("stringDate1DayBefore = {} ", stringDate1DayBefore);
		log.info("stringDate2DayBefore = {} ", stringDate3DayBefore);

		urlBuffer.append(GlobalConstants.COVID_API_URL);

		urlBuffer.append(stringDate3DayBefore);
		urlBuffer.append("&to=");
		urlBuffer.append(stringDate1DayBefore);

		log.info("urlBuffer = {} ", urlBuffer.toString());
		String json = ResffulServices.getServices(urlBuffer.toString());
		log.info("json = {} ", json);

		ObjectMapper mapper = new ObjectMapper();

		CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, Covid19ApiModel.class);

		List<Covid19ApiModel> cases = mapper.readValue(json, javaType);

		log.info("cases  = {} ", cases.size());

		Covid19ApiModel first = cases.get(0);
		Covid19ApiModel last = cases.get(1);

		log.info("first cases ={}, last cases= {} ", first.getCases(), last.getCases());

		int totalCases = last.getCases() - first.getCases();

		log.info("Total Cases = {} ({})", totalCases, date1DayBefore.toString());
		assertTrue(totalCases != 0);

	}
}
