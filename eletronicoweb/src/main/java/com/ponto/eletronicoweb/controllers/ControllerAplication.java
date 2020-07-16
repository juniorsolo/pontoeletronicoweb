package com.ponto.eletronicoweb.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ControllerAplication {

	@RequestMapping("/")
	public String home2() {
		return "welcome to home /";
	}
	@RequestMapping("/index")	
	public String home3() {
		return "welcome to home index";
	}
}
 