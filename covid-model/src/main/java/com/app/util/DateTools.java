package com.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTools {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DateTools.class);

	private final static String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
	private DateTools() {
		    throw new IllegalStateException("Utility class");
		  }

	public static Date minusDate(int day) {

		LocalDate localDate = LocalDate.now().minusDays(day);

		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		log.info("minusDate date={} ", date);
		return date;
	}

	public static String getDate(String format, Date date) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

		String stringDate = simpleDateFormat.format(date);

		log.info("getDate ={} ", stringDate);

		return stringDate;

	}

	public static String getDefaultDate() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(API_DATE_FORMAT);
		
		String stringDate = simpleDateFormat.format(new Date());

		log.info("getDefaultDate ={} ", stringDate);

		return stringDate;

	}
	
	public static Date convertDate(String dateString, String defaultFormat) throws ParseException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultFormat);

		Date date = simpleDateFormat.parse(dateString);

		log.info("testConvertDateFormat ends. date ={}", date);

		return date;
	}
	
	public static LocalDate convertToLocalDate(Date dateToConvert) {
		return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}

	public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
	    return LocalDateTime.ofInstant(
	      dateToConvert.toInstant(), ZoneId.systemDefault());
	}
}
