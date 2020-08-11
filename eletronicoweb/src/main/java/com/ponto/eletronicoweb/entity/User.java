package com.ponto.eletronicoweb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ponto.eletronicoweb.enums.ProfileEnum;

/**
 * Represent the User from system.
 * 
 * @author junior
 *
 */
@Document
public class User {
	
	@Id
	private String id;
	
	private String login;
	
	private String password;
	
	private ProfileEnum profile;
	
	
	
	/**
	 * 
	 * Getters and Setters
	 */	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}
	
	
	
}
