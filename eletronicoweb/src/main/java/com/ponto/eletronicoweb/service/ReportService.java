package com.ponto.eletronicoweb.service;

import java.io.FileNotFoundException;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {
	
	public String exportReportRegistryByUser(String reportFormat, String userId) throws FileNotFoundException, JRException;
	
}
