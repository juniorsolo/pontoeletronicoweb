package com.ponto.eletronicoweb.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
	
	/*
	 *  Date with hours the registry
	 */
	private LocalDateTime date;
	
	@DBRef
	private User user;
	
	
	//Getter and Setters
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
