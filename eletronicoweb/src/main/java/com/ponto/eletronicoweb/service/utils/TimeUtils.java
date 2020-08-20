package com.ponto.eletronicoweb.service.utils;

import java.time.Duration;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtils {
	private static Logger log = LoggerFactory.getLogger(TimeUtils.class);
	
	
	public static Long extractNanoDiferenceHours(String hour1, String hour2) {
		try{
			LocalTime hora = LocalTime.parse(hour1);
			LocalTime hora2 = LocalTime.parse(hour2);			
			Duration duration = Duration.between(hora, hora2);
			
			return duration.toNanos();
		}catch (Exception e) {
			log.error(e.getMessage());
			return 0L;
		}
	}
	
}
