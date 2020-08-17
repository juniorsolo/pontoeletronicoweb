package com.ponto.eletronicoweb.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.ponto.eletronicoweb.entity.Registry;
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
	
		
	public String exportReportRegistryByUser(String reportFormat, String userId)  throws FileNotFoundException, JRException{
		
		Pageable pages = PageRequest.of(0, 200);
		//Page<Registry> registryList = registryService.findPeriodByUserId(DateUtils.firstDateOfMonth(), DateUtils.lastDateOfMonth(), userId, pages);
		
		File file = ResourceUtils.getFile("classpath:FolhaDePonto.jrxml");
		
		JasperReport jasperReport = JasperCompileManager.compileReport( file.getAbsolutePath());
		//JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource((Collection<Registry>) registryList);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("NomeEmpresa", "Ótica HeLo");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
		
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, "c:\\dev\\" + "folha_de_ponto.html");
		}else if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, "c:\\dev\\" + "folha_de_ponto.pdf");
		}
		
		return "report generate with success!";
	}
}
