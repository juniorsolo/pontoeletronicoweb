package com.ponto.eletronicoweb.entity;

import java.util.List;

/**
 * 
 * @author junior 16/07/2020
 *
 */

public class Subsidiary {
	
	
	private String name;	
	

	private List<Employee> employeeList;
	
	
	//Getters and Setters

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
