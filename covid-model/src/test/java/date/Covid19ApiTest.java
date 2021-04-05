package date;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.app.model.api.Covid19ApiModel;
import com.app.util.DateTools;
import com.app.util.ResffulServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

@Disabled("")
class Covid19ApiTest {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DateTools.class);

	private final static String URL = "https://api.covid19api.com/total/country/malaysia/status/confirmed?from=";

	@Disabled("")
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

		urlBuffer.append(URL);

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
