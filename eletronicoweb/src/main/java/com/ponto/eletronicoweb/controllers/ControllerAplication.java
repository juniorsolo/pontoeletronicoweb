package com.ponto.eletronicoweb.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ponto.eletronicoweb.entity.Empresa;
import com.ponto.eletronicoweb.entity.Filial;
import com.ponto.eletronicoweb.service.impl.EmpresaServiceimpl;

@RestController
@CrossOrigin(origins = "*")
public class ControllerAplication {
	
	@Autowired
	private EmpresaServiceimpl empresaService;

	@RequestMapping("/")
	public String home2() {
		return "welcome to home /";
	}
	@RequestMapping("/index")	
	public String home3() {
		 Empresa e = new Empresa();
		 e.setCnpj(12345678901234L);
		 e.setRazaoSocial("Ótica Mendonça");
		 
		 Filial f = new Filial();
		 f.setNome("M1");
		 e.setFiliais(new ArrayList<>());
		 e.getFiliais().add(f);
		 
		 e = empresaService.createOrUpdate(e);
		 empresaService.createOrUpdate(e);
		return "welcome to home index";
	}
}
 