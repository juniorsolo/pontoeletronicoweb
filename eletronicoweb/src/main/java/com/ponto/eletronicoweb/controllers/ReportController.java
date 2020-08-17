package com.ponto.eletronicoweb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.eletronicoweb.response.Response;
import com.ponto.eletronicoweb.service.ReportService;
import com.ponto.eletronicoweb.service.UserService;

@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "*")
public class ReportController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	@Autowired
	ReportService reportService;
	
	@GetMapping(value = "{userId}/{format}")
	public ResponseEntity<Response<String>> reportByUserId(@PathVariable String userId,@PathVariable String format){
		Response<String> response = new Response<>();

		try {
			
			response.setData(reportService.exportReportRegistryByUser(format, userId));
			log.info("find registry by period with success.");
		}catch (Exception e) {
			log.error("Error find registry. " + e.getMessage());
			response.getErrors().add(e.getMessage());
			ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
