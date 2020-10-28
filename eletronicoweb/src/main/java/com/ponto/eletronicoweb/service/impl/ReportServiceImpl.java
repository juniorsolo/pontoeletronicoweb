package com.ponto.eletronicoweb.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Month;
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
		
	public String exportReportRegistryByUser(String reportFormat, String userId)  throws FileNotFoundException, JRException, Exception{
		
		
		Company company = companyService.findByUserLogin(4444L);
		if(company == null) {
			throw new Exception("cannot find company by login for make report.");
		}
		
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
		parameters.put("corporationName", company.getName());
		parameters.put("corporationDocument", company.getRegisterNumber().toString());
		parameters.put("corporationAddress", company.getAddress().getType() + " " + company.getAddress().getName() + ", " + company.getAddress().getNumber());
		parameters.put("corporationZipCode", company.getAddress().getPostalCode());
		parameters.put("corporationCity", company.getAddress().getCity());
		parameters.put("corporationState", company.getAddress().getUf().toString());
		parameters.put("employeeOccupation", company.getSubsidiaryList().get(0).getEmployeeList().get(0).getOccupation());
		parameters.put("employeeName", company.getSubsidiaryList().get(0).getEmployeeList().get(0).getName());
		parameters.put("employeeDocument", company.getSubsidiaryList().get(0).getEmployeeList().get(0).getDocumentNumber().toString());
		parameters.put("monthYearReport", Month.of(month) +"-"+ year);

		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, "c:\\dev\\" + "folha_de_ponto.html");
		}else if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, "c:\\dev\\" + "folha_de_ponto.pdf");
		}
		
		return "report generate with success!";
	}
	

}
