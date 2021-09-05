package test;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
class DateTest {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DateTest.class);

	@Test
	void testDate() {
		String defaultTime = "T00:00:00Z";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String date = simpleDateFormat.format(new Date());

		log.info("format dated = " + date + defaultTime);

		assertNotNull(date);
	}

	@Test
	void testMinusDate() {
		String defaultTime = "T00:00:00Z";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		LocalDate localDate = LocalDate.now().minusDays(1);

		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		String stringDate = simpleDateFormat.format(date);

		log.info("testMinusDate date = " + stringDate + defaultTime);

		assertNotNull(date);
	}

	@Test
	void testConvertDateFormat() throws ParseException {
		String dateString = "2020-12-19T00:00:00Z";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date date = simpleDateFormat.parse(dateString);

		log.info("testConvertDateFormat ends. date ={}", date);

		assertNotNull(date);
	}
}
