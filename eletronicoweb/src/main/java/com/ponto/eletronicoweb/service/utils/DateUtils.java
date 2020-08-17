package com.ponto.eletronicoweb.service.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtils {
		

	public static LocalDateTime firstDateOfMonth() {
		LocalDate now = LocalDate.now();
		return LocalDateTime.of(now.getYear(), now.getMonth(), 01, 23, 59);
	}
	
	public static LocalDateTime lastDateOfMonth() {
		LocalDate now = LocalDate.now();
		LocalDate last = now.withDayOfMonth(now.lengthOfMonth());
		return LocalDateTime.of(last.getYear(), last.getMonth(), last.getDayOfMonth(), 23, 59);
	}
}
