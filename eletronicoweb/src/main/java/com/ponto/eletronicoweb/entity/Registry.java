package com.ponto.eletronicoweb.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 12/08/2020
 * 
 * @author junior
 *
 * The Object where Register are keeps. 
 */
@Document
public class Registry {
	
	@Id
	private String id;
	
	private LocalDateTime data;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	
	
}
