package com.ponto.eletronicoweb.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.ponto.eletronicoweb.dto.ConverterRegistry;
import com.ponto.eletronicoweb.dto.RegistryDTO;
import com.ponto.eletronicoweb.entity.Company;
import com.ponto.eletronicoweb.entity.Registry;
import com.ponto.eletronicoweb.service.CompanyService;
import com.ponto.eletronicoweb.service.RegistryService;
import com.ponto.eletronicoweb.service.ReportService;
import com.ponto.eletronicoweb.service.utils.DateUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	RegistryService registryService;
	
	@Autowired
	CompanyService companyService;
		
	public String exportReportRegistryByUser(String reportFormat, String userId)  throws FileNotFoundException, JRException{
		
		
		Company company = companyService.findByUserLogin(4444L);
		System.out.println(company.getName());
		
		int month = 8;
		int year = 2020;
		List<Registry> registryList = registryService.findPeriodByUserIdReport(DateUtils.firstDateOfMonth(month, year), DateUtils.lastDateOfMonth(month, year), userId);
		List<RegistryDTO> registryDTOList = new ArrayList<>();
		
		if(registryList != null && !registryList.isEmpty()) {
			registryDTOList = ConverterRegistry.forRegistryDTO(registryList, month, year);
		}
		
		File file = ResourceUtils.getFile("classpath:FolhaDePonto.jrxml");
		
		JasperReport jasperReport = JasperCompileManager.compileReport( file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(registryDTOList);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("corporationName", "Óticas Beatriz LTDA.");
		parameters.put("corporationDocument", "27.712.011/0001-50");
		parameters.put("corporationAddress", "Rua Cerqueira Cesar, 105");
		parameters.put("corporationZipCode", "06823-800");
		parameters.put("corporationCity", "São Paulo");
		parameters.put("corporationState", "SP");
		parameters.put("employeeOccupation", "Gerente");
		parameters.put("employeeName", "Taynan Tayne Pereira da Silva");
		parameters.put("employeeDocument", "453.654.789-80");
		parameters.put("monthYearReport", "Agosto-2020");

		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, "c:\\dev\\" + "folha_de_ponto.html");
		}else if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, "c:\\dev\\" + "folha_de_ponto.pdf");
		}
		
		return "report generate with success!";
	}
	

}
