package com.ponto.eletronicoweb.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author junior
 *
 */
@Document
public class Subsidiary {
	
	@Id
	private String id;
	
	private String name;	
	

	private List<Employee> employeeList;
	
	//Getters and Setters
	
	public String getId() {
		return id;
	}    

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	
	
}
