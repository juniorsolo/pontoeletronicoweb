package com.ponto.eletronicoweb.service;

import java.util.Optional;

import com.ponto.eletronicoweb.entity.User;

public interface UserService {
	public User create(User user) throws Exception;
	public User update(User user) throws Exception;
	public Optional<User> findById(String id);
	public void delete(String id);
	public User findByLoginAndPassword(String login, String password) throws Exception;
}
