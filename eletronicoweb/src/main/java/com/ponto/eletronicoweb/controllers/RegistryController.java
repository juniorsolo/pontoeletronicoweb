package com.ponto.eletronicoweb.controllers;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.eletronicoweb.entity.Registry;
import com.ponto.eletronicoweb.entity.User;
import com.ponto.eletronicoweb.response.Response;
import com.ponto.eletronicoweb.service.RegistryService;
import com.ponto.eletronicoweb.service.UserService;
import com.ponto.eletronicoweb.service.utils.DateUtils;

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
	public ResponseEntity<Response<Registry>> create(@RequestBody User user){
		Response<Registry> response = new Response<>();
		try {
			
		   User userFinded = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
			
			
			Registry regis = new Registry();
			regis.setDate(LocalDateTime.now());
			regis.setUser(userFinded);
		
			regis = registryService.create(regis);
			response.setData(regis);
			log.info("Create registry with success.");
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
			log.info("Find Registry by id with success.");
		}catch (Exception e) {
			log.error("Error find registry. " + e.getMessage());
			response.getErrors().add(e.getMessage());
			ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "period/{idUser}")
	public ResponseEntity<Response<Page<Registry>>> findByPeriod(@PathVariable String idUser){
		Response<Page<Registry>> response = new Response<>();
		Page<Registry> registryList = null;
		try {
			LocalDateTime startDate = DateUtils.firstDateOfMonth();
			LocalDateTime endDate = DateUtils.lastDateOfMonth();
			
			Pageable pages = PageRequest.of(0, 30);
			
		    registryList = registryService.findPeriodByUserId(startDate, endDate, idUser, pages);
			
			response.setData(registryList);
			log.info("find registry by period with success.");
		}catch (Exception e) {
			log.error("Error find registry. " + e.getMessage());
			response.getErrors().add(e.getMessage());
			ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
}
