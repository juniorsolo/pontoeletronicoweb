package com.ponto.eletronicoweb.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Employee {
		
	private Long documentNumber;
	private String name;
	private String occupation;
	private Integer login;
	private String password;
	
	
	//Getters and Setters

	public Long getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(Long documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public Integer getLogin() {
		return login;
	}
	public void setLogin(Integer login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
