package com.ponto.eletronicoweb.controllers;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.eletronicoweb.entity.Company;
import com.ponto.eletronicoweb.response.Response;
import com.ponto.eletronicoweb.service.impl.CompanyServiceImpl;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "*")
public class CompanyController {
	
	@Autowired
	private CompanyServiceImpl companyService;

	
	
	@PostMapping()	
	public ResponseEntity<Response<Company>> create(@RequestBody Company company, BindingResult result) {
		Response<Company> response = new Response<>();	
		 try {
			 this.validadeCreate(company, result);
			 if (result.hasErrors()) {
					result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
					return ResponseEntity.badRequest().body(response);
			 }
			 company = companyService.create(company);
			 response.setData(company);
		 }catch (Exception e) {
			response.getErrors().add("Company it can't be create. " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validadeCreate(Company company, BindingResult result) {
		if(company == null) {
			result.addError(new ObjectError("Company", "Object it can't be null."));
			return;
		}
		if(company.getRegisterNumber() == null || company.getRegisterNumber() == 0) {
			result.addError(new ObjectError("Company", "Register Number it can't be null."));			
		}
		if(StringUtils.isAllBlank(company.getName())) {
			result.addError(new ObjectError("Company", "Name it can't be null or empty."));
		}
	}
	
	@PutMapping
	public ResponseEntity<Response<Company>> update(@RequestBody Company company, BindingResult result){
		Response<Company> response = new Response<>();
		try {
			
			this.validateUpdate(company, result);
			
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			company = companyService.update(company);
			response.setData(company);
			
		}catch (Exception e) {
			response.getErrors().add("Company it can't be updated. " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validateUpdate(Company company, BindingResult result) {
		if(company == null) {
			result.addError(new ObjectError("Company", "Object it can't be null."));
			return;
		}
		if(StringUtils.isAllBlank(company.getId())) {
			result.addError(new ObjectError("Company", "ID can't be null or empty."));
		}
		if(company.getRegisterNumber() == null || company.getRegisterNumber() == 0) {
			result.addError(new ObjectError("Company", "Company's registerNumber can't be null or empty."));
		}
		if(StringUtils.isAllBlank(company.getName())) {
			result.addError(new ObjectError("Company", "Company's name not be null or empty"));
		}
	}
	
	@GetMapping(value= "{id}")
	public ResponseEntity<Response<Company>> findById(@PathVariable("id") String id){
		Response<Company> response = new Response<>();
		try {
			if(StringUtils.isAllBlank(id)) {
				response.getErrors().add("Find by id: id it can't be null or empty. " + id);
				throw new Exception("Paramter id invalid:" + id);
			}
			Optional<Company> companyOptional = companyService.findById(id);
			
			if(!companyOptional.isPresent()) {
				response.getErrors().add("Register not found with id: " + id);
				throw new Exception("Register not found by id:" + id);
			}
			
			response.setData(companyOptional.get());
			
		}catch (Exception e) {
			response.getErrors().add("Error find company by id. " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value= "register/{registerNumber}")
	public ResponseEntity<Response<Company>> findByRegisterNumber(@PathVariable("registerNumber") Long registerNumber){
		Response<Company> response = new Response<>();
		try {
			
			if(registerNumber == null) {
				response.getErrors().add("Find by registerNumber: registerNumber it can't be null or empty. " + registerNumber);
				throw new Exception("Paramter registerNumber invalid:" + registerNumber);
			}
			Company company = companyService.findByRegisterNumber(registerNumber);
			
			if(company == null){
				response.getErrors().add("Register not found with registerNumber: " + registerNumber);
				throw new Exception("Register not found by registerNumber:" + registerNumber);
			}
			
			response.setData(company);
		}catch (Exception e) {
			response.getErrors().add("Error find by company by registerNumber. " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id){
		Response<String> response = new Response<>();
		try {
			if(StringUtils.isAllBlank(id)) {
				response.getErrors().add("Paramter Id invalid.");
			    throw new Exception("Paramter Id invalid.");
			}
			Optional<Company> opt = companyService.findById(id);
			if(!opt.isPresent()) {
				response.getErrors().add("Company not found for delete, id not exist.");
				throw new Exception("Company not found for delete, id not exist."); 
			}
			
			companyService.delete(id);
						
		}catch (Exception e) {
			response.getErrors().add("Error for delete company. " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
 