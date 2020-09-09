package com.ponto.eletronicoweb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ponto.eletronicoweb.entity.User;

/**
 * create in 11/08/2020
 * 
 * @author junior
 *
 */
public interface UserRepository extends MongoRepository<User, String>{
	
	public User findByLoginAndPassword(String login, String password);
	public Optional<User> findByLogin(String login);	
}
