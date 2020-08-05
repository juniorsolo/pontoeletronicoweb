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

import com.ponto.eletronicoweb.entity.Empresa;
import com.ponto.eletronicoweb.response.Response;
import com.ponto.eletronicoweb.service.impl.EmpresaServiceimpl;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin(origins = "*")
public class EmpresaController {
	
	@Autowired
	private EmpresaServiceimpl empresaService;

	
	
	@PostMapping()	
	public ResponseEntity<Response<Empresa>> create(@RequestBody Empresa empresa, BindingResult result) {
		Response<Empresa> response = new Response<>();	
		 try {
			 this.validadeCreate(empresa, result);
			 if (result.hasErrors()) {
					result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
					return ResponseEntity.badRequest().body(response);
			 }
			 empresa = empresaService.create(empresa);
			 response.setData(empresa);
		 }catch (Exception e) {
			response.getErrors().add("Empresa it can't be create. " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	private void validadeCreate(Empresa empresa, BindingResult result) {
		if(empresa == null) {
			result.addError(new ObjectError("Empresa", "Object it can't be null."));
			return;
		}
		if(empresa.getCnpj() == null || empresa.getCnpj() == 0) {
			result.addError(new ObjectError("Empresa", "CNPJ it can't be null."));			
		}
		if(StringUtils.isAllBlank(empresa.getRazaoSocial())) {
			result.addError(new ObjectError("Empresa", "Razao Social it can't be null or empty."));
		}
	}
	
	@PutMapping
	public ResponseEntity<Response<Empresa>> update(@RequestBody Empresa empresa, BindingResult result){
		Response<Empresa> response = new Response<>();
		try {
			
			this.validateUpdate(empresa, result);
			
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.ok(response);
	}
	
	private void validateUpdate(Empresa empresa, BindingResult result) {
		if(empresa == null) {
			result.addError(new ObjectError("Empresa", "Object it can't be null."));
			return;
		}
		if(StringUtils.isAllBlank(empresa.getId())) {
			result.addError(new ObjectError("Empresa", "ID can't be null or empty."));
		}
		if(empresa.getCnpj() == null || empresa.getCnpj() == 0) {
			result.addError(new ObjectError("Empresa", "CNPJ can't be null or empty."));
		}
		if(StringUtils.isAllBlank(empresa.getRazaoSocial())) {
			result.addError(new ObjectError("Empresa", "Razao Social not be null or empty"));
		}
	}
	
	@GetMapping(value= "{id}")
	public ResponseEntity<Response<Empresa>> findById(@PathVariable("id") String id){
		Response<Empresa> response = new Response<>();
		try {
			if(StringUtils.isAllBlank(id)) {
				response.getErrors().add("Find by id: id it can't be null or empty. " + id);
				throw new Exception("Paramter id invalid:" + id);
			}
			Optional<Empresa> empresa = empresaService.findById(id);
			
			if(!empresa.isPresent()) {
				response.getErrors().add("Register not found with id: " + id);
				throw new Exception("Register not found by id:" + id);
			}
			
			response.setData(empresa.get());
		}catch (Exception e) {
			response.getErrors().add("Error find empresa by id. " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value= "cnpj/{cnpj}")
	public ResponseEntity<Response<Empresa>> findByCnpj(@PathVariable("cnpj") Long cnpj){
		Response<Empresa> response = new Response<>();
		try {
			
			if(cnpj == null) {
				response.getErrors().add("Find by cnpj: cnpj it can't be null or empty. " + cnpj);
				throw new Exception("Paramter cnpj invalid:" + cnpj);
			}
			Empresa empresa = empresaService.findByCnpj(cnpj);
			
			if(empresa == null){
				response.getErrors().add("Register not found with cnpj: " + cnpj);
				throw new Exception("Register not found by cnpj:" + cnpj);
			}
			
			response.setData(empresa);
		}catch (Exception e) {
			response.getErrors().add("Error find by empresa by CNPJ. " + e.getMessage());
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
			Optional<Empresa> opt = empresaService.findById(id);
			if(!opt.isPresent()) {
				response.getErrors().add("Register not found for delete, id not exist.");
				throw new Exception("Register not found for delete, id not exist."); 
			}
			
			empresaService.delete(id);
						
		}catch (Exception e) {
			response.getErrors().add("Erro delete empresa. " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
 