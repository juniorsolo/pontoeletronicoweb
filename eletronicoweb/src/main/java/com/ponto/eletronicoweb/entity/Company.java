package com.ponto.eletronicoweb.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
/**
 * 
 * @author junior
 *
 */
@Document
public class Company {
	
	@Id
	private String id;
	
	//14
	@NonNull
	private Long registerNumber;
	
	@NonNull
	private String name;
	
	private Address address;
	
	@DBRef(lazy = false)
	private List<Subsidiary> subsidiaryList;

	
	
	//Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(Long registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Subsidiary> getSubsidiaryList() {
		return subsidiaryList;
	}

	public void setSubsidiaryList(List<Subsidiary> subsidiaryList) {
		this.subsidiaryList = subsidiaryList;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
}
