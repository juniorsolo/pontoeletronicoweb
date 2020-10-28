package com.ponto.eletronicoweb.entity;

import com.ponto.eletronicoweb.enums.AddressTypeEnum;
import com.ponto.eletronicoweb.enums.UFEnum;
/**
 * 
 * @author junior 22/08/2020
 *
 */
public class Address {
		
	
	private String name;
	
	private AddressTypeEnum type; 
		
	private Integer number;
	
	private String city;
	
	private String postalCode;
	
	private UFEnum uf;


	//Getters and Setters
	
	public String getName() {
		return name;    
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressTypeEnum getType() {
		return type;
	}

	public void setType(AddressTypeEnum type) {
		this.type = type;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public UFEnum getUf() {
		return uf;
	}

	public void setUf(UFEnum uf) {
		this.uf = uf;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

		
}
