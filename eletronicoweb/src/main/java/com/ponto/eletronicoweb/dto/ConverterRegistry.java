package com.ponto.eletronicoweb.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ponto.eletronicoweb.entity.Registry;
import com.ponto.eletronicoweb.service.utils.DateTimeFormatUtil;
import com.ponto.eletronicoweb.service.utils.DateUtils;

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

	public static List<RegistryDTO> forRegistryDTO(List<Registry> list) {

		List<RegistryDTO> listaDTO = new ArrayList<>();

		try {

			for (Registry registry : list) {
				if (dayCurrent == null) {
					dayCurrent = registry.getDate();
					registryDTO = new RegistryDTO();
					registryDTO.setDay(registry.getDate().format(DateTimeFormatUtil.getDayMonthFormatter()) + "-" + DateUtils.getDayOfWeek(registry.getDate()));
					registryDTO.setRegister1(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
					count = 1;
				}

				if (DateUtils.equalsDate(dayCurrent, registry.getDate())) {

					if (count == 2) {
						registryDTO.setRegister2(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
					} else if (count == 3) {
						registryDTO.setRegister3(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
					} else if (count == 4) {
						registryDTO.setRegister4(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
					} else if (count == 5) {
						registryDTO.setRegister5(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
					} else if (count == 6) {
						registryDTO.setRegister6(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
					}

					count = count + 1;
				} else {
					listaDTO.add(registryDTO);
					dayCurrent = registry.getDate();
					registryDTO = new RegistryDTO();
					registryDTO.setDay(registry.getDate().format(DateTimeFormatUtil.getDayMonthFormatter()) + "-" + DateUtils.getDayOfWeek(registry.getDate()));
					registryDTO.setRegister1(registry.getDate().format(DateTimeFormatUtil.getHourMinuteFormatter()));
					count = 2;
				}

			}
			listaDTO.add(registryDTO);  //include the last register
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listaDTO;
	}

}
