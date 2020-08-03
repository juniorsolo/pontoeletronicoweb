package com.ponto.eletronicoweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.eletronicoweb.entity.Empresa;
import com.ponto.eletronicoweb.response.Response;
import com.ponto.eletronicoweb.service.impl.EmpresaServiceimpl;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin(origins = "*")
public class ControllerAplication {
	
	@Autowired
	private EmpresaServiceimpl empresaService;

	
	
	@PostMapping()	
	public ResponseEntity<Response<Empresa>> create(@RequestBody Empresa empresa, BindingResult result) {
		Response<Empresa> response = new Response<>();	
		 try {
			 
			 empresa = empresaService.createOrUpdate(empresa);
			 response.setData(empresa);
		 }catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value= "{id}")
	public ResponseEntity<Response<Empresa>> findById(@PathVariable("id") String id){
		Response<Empresa> response = new Response<>();
		try {
			Empresa e = empresaService.findById(id);
			response.setData(e);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value= "cnpj/{cnpj}")
	public ResponseEntity<Response<Empresa>> findByCnpj(@PathVariable("cnpj") Long cnpj){
		Response<Empresa> response = new Response<>();
		try {
			Empresa e = empresaService.findByCnpj(cnpj);
			response.setData(e);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
 