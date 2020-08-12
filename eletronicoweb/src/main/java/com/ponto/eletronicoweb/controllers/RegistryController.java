package com.ponto.eletronicoweb.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.eletronicoweb.entity.Registry;
import com.ponto.eletronicoweb.response.Response;
import com.ponto.eletronicoweb.service.RegistryService;
import com.ponto.eletronicoweb.service.UserService;

/**
 *     12/08/2020
 * 
 * @author junior
 *
 */
@RestController
@RequestMapping("/api/registry")
@CrossOrigin(origins = "*")
public class RegistryController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RegistryService registryService;
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<Response<Registry>> create(){
		Response<Registry> response = new Response<>();
		try {
			Registry regis = new Registry();
			regis.setData(LocalDateTime.now());
			regis.setUser(userService.findById("5f3315d2d44ead50ef7a2217").get());
		
			regis = registryService.create(regis);
			response.setData(regis);
		}catch (Exception e) {
			log.error("Error create registry. " + e.getMessage());
			response.getErrors().add(e.getMessage());
			ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Registry>> findById(@PathVariable String id){
		Response<Registry> response = new Response<>();
		try {
			Optional<Registry> regis = registryService.findById(id);
			
			response.setData(regis.get());
		}catch (Exception e) {
			log.error("Error find registry. " + e.getMessage());
			response.getErrors().add(e.getMessage());
			ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
