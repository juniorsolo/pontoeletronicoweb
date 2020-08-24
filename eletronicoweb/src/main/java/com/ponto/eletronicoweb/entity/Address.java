package com.ponto.eletronicoweb.entity;

import com.ponto.eletronicoweb.enums.AddressTypeEnum;
/**
 * 
 * @author junior 22/08/2020
 *
 */
public class Address {
		
	
	private String name;
	
	private AddressTypeEnum typeEnum; 
		
	private Integer number;
	
	private String city;
	
	private String UF;


	//Getters and Setters
	
	public String getName() {
		return name;    
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(AddressTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
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

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}	
	
}
