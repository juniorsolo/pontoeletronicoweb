package com.ponto.eletronicoweb.service;

import java.util.Optional;

import com.ponto.eletronicoweb.entity.Registry;

public interface RegistryService {
	
	public Registry create(Registry registry) throws Exception;
	public Registry update(Registry registry) throws Exception;
	public Optional<Registry> findById(String id);
	public void deleteById(String id);
	
}
