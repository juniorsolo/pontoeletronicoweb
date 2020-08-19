package com.ponto.eletronicoweb.service.utils;

import java.time.format.DateTimeFormatter;

/**
 * 
 * @author junior 18/08/2020
 * 
 * Format pattern for Date and time.
 */
public class DateTimeFormatUtil {
	

	private static String DAY_MONTH_YEAR = "dd/MM/yyyy";
	private static String DAY_MONTH = "dd/MM";
	private static String HOUR_MINUTE = "HH:mm"; 
	 
	public static DateTimeFormatter getDateFormatter() {
		return DateTimeFormatter.ofPattern(DAY_MONTH_YEAR);
	}
	
	public static DateTimeFormatter getDayMonthFormatter() {
		return DateTimeFormatter.ofPattern(DAY_MONTH);
	}
	
	public static DateTimeFormatter getHourMinuteFormatter() {
		return DateTimeFormatter.ofPattern(HOUR_MINUTE);
	}
	
}
