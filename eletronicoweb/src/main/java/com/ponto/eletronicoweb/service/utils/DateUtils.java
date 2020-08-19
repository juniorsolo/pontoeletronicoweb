package com.ponto.eletronicoweb.service.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author junior 18/08/2020
 * 
 * Utility class for different types of dates.
 */
public class DateUtils {
	private static Logger log = LoggerFactory.getLogger(DateUtils.class);		

	public static LocalDateTime firstDateOfMonth() {
		LocalDate now = LocalDate.now();
		return LocalDateTime.of(now.getYear(), now.getMonth(), 01, 23, 59);
	}
	
	public static LocalDateTime lastDateOfMonth() {
		LocalDate now = LocalDate.now();
		LocalDate last = now.withDayOfMonth(now.lengthOfMonth());
		return LocalDateTime.of(last.getYear(), last.getMonth(), last.getDayOfMonth(), 23, 59);
	}
	
	public static boolean equalsDate(LocalDateTime data1, LocalDateTime data2) {
		try {
			return data1.format(DateTimeFormatUtil.getDateFormatter()).equals(data2.format(DateTimeFormatUtil.getDateFormatter()));
		}catch (Exception e) {
			log.error(e.getMessage());			
		}
		return false;
	}
	
	public static  String getDayOfWeek(LocalDateTime data) {
		String dayOfWeek = "";
		try {
			switch (data.getDayOfWeek().getValue()) {
			case 1:
				dayOfWeek = "Segunda"; 
				break;
			case 2:
				dayOfWeek = "Terça"; 
				break;
			case 3:
				dayOfWeek = "Quarta"; 
				break;
			case 4:
				dayOfWeek = "Quinta"; 
				break;
			case 5:
				dayOfWeek = "Sexta"; 
				break;
			case 6:
				dayOfWeek = "Sábado"; 
				break;
			case 7:
				dayOfWeek = "Domingo"; 
				break;
											
			default:
				break;
			} 
		 return dayOfWeek;	
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return "";
	}
}
