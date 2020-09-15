package com.ponto.eletronicoweb.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ponto.eletronicoweb.entity.Registry;
import com.ponto.eletronicoweb.service.utils.DateTimeFormatUtil;
import com.ponto.eletronicoweb.service.utils.DateUtils;
import com.ponto.eletronicoweb.service.utils.TimeUtils;

/**
 * 
 * @author junior 18/08/2020
 *
 *    Converter registry for registryDTO and others.
 */
public class ConverterRegistry {

	private static Logger log = LoggerFactory.getLogger(ConverterRegistry.class);
	private static RegistryDTO registryDTO;
	private static LocalDateTime dayCurrent;
	private static int count = 1;
	private static Long sum= 0L;

	public static List<RegistryDTO> forRegistryDTO(List<Registry> list) {

		List<RegistryDTO> listaDTO = new ArrayList<>();

		try {
			
			clearfields();
			
			for (Registry registry : list) {
				if (dayCurrent == null) {
					dayCurrent = registry.getDate();
					registryDTO.setDayFormated(registry.getDate().format(DateTimeFormatUtil.getDayMonthFormatter()) + "-" + DateUtils.getDayOfWeek(registry.getDate()));
					registryDTO.setRegister1(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
				}

				if (DateUtils.equalsDate(dayCurrent, registry.getDate())) {

					if (count == 2) {
						registryDTO.setRegister2(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
						sum = TimeUtils.extractNanoDiferenceHours(registryDTO.getRegister1(), registryDTO.getRegister2());
					} else if (count == 3) {
						registryDTO.setRegister3(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
					} else if (count == 4) {
						registryDTO.setRegister4(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
						sum = sum + TimeUtils.extractNanoDiferenceHours(registryDTO.getRegister3(), registryDTO.getRegister4());
					} else if (count == 5) {
						registryDTO.setRegister5(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
					} else if (count == 6) {
						registryDTO.setRegister6(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
						sum = sum + TimeUtils.extractNanoDiferenceHours(registryDTO.getRegister5(), registryDTO.getRegister6());
					}

					count = count + 1;
				} else {
					if(!sum.equals(0L)) {
						registryDTO.setDaySum(LocalTime.ofNanoOfDay(sum).toString());
					}
					listaDTO.add(registryDTO);
					sum = 0L;
					dayCurrent = registry.getDate();
					registryDTO = new RegistryDTO();
					registryDTO.setDayFormated(registry.getDate().format(DateTimeFormatUtil.getDayMonthFormatter()) + "-" + DateUtils.getDayOfWeek(registry.getDate()));
					registryDTO.setRegister1(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
					count = 2;
				}

			}
			if(!sum.equals(0L)) {
				registryDTO.setDaySum(LocalTime.ofNanoOfDay(sum).toString());
			}
			listaDTO.add(registryDTO);  //include the last register
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listaDTO;
	}
	private static void clearfields() {
		dayCurrent = null;
		registryDTO = new RegistryDTO();
		count = 1;
		sum= 0L;
	}
	
	public static List<RegistryDTO> forRegistryDTONew(List<Registry> list, int month, int year) {
		List<RegistryDTO> registryList = daysOfMonth(month, year);
		
		try {
			
			for(RegistryDTO dayOfMonth : registryList) {
				count = 1;
				sum= 0L;
				for(Registry registry : list) {
					if(DateUtils.equalsDate(dayOfMonth.getDay(), registry.getDate())) {
						
						if (count == 1) {
							dayOfMonth.setRegister1(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));							
						}
						if (count == 2) {
							dayOfMonth.setRegister2(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
							sum = TimeUtils.extractNanoDiferenceHours(dayOfMonth.getRegister1(), dayOfMonth.getRegister2());
							dayOfMonth.setDaySum(LocalTime.ofNanoOfDay(sum).toString());
						} else if (count == 3) {
							dayOfMonth.setRegister3(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
						} else if (count == 4) {
							dayOfMonth.setRegister4(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
							sum = sum + TimeUtils.extractNanoDiferenceHours(dayOfMonth.getRegister3(), dayOfMonth.getRegister4());
							dayOfMonth.setDaySum(LocalTime.ofNanoOfDay(sum).toString());
						} else if (count == 5) {
							dayOfMonth.setRegister5(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
						} else if (count == 6) {
							dayOfMonth.setRegister6(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
							sum = sum + TimeUtils.extractNanoDiferenceHours(dayOfMonth.getRegister5(), dayOfMonth.getRegister6());
							dayOfMonth.setDaySum(LocalTime.ofNanoOfDay(sum).toString());
						}
						count++;
					}
				}
			}
			
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return registryList;
	}
	public static List<RegistryDTO>  daysOfMonth(int month, int year) {
		List<RegistryDTO> lista = new ArrayList<>();
		
		RegistryDTO dto;
		LocalDateTime dayMonth = DateUtils.firstDateOfMonth(month, year);
		LocalDateTime lastDay = DateUtils.lastDateOfMonth(month, year);
		
		for(int day = 1 ; day <= lastDay.getDayOfMonth(); day++) {
			dto = new RegistryDTO();
			dayMonth = LocalDateTime.of(year, month, day, 00, 00);
			dto.setDay(dayMonth);
			dto.setDayFormated(dayMonth.format(DateTimeFormatUtil.getDayMonthFormatter()));
			dto.setDayWeek(DateUtils.getDayOfWeek(dayMonth));
			lista.add(dto);
		}
		
		
		return lista;
	}
}
