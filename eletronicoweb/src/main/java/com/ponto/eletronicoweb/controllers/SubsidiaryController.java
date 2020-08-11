package com.ponto.eletronicoweb.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.eletronicoweb.entity.Subsidiary;
import com.ponto.eletronicoweb.response.Response;
import com.ponto.eletronicoweb.service.impl.SubsidiaryServiceImpl;

@RestController
@RequestMapping("api/subsidiary")
@CrossOrigin(origins = "*")
public class SubsidiaryController {
	
	@Autowired
	private SubsidiaryServiceImpl subsidiaryService;
	
	@GetMapping(value="{id}")
	public ResponseEntity<Response<Subsidiary>> findByID(@PathVariable("id") String id){
		Response<Subsidiary> response = new Response<>();
		
		Optional<Subsidiary> sub = subsidiaryService.findById(id);
		response.setData(sub.get());
		return ResponseEntity.ok(response);
	}
}
