package com.ponto.eletronicoweb.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ponto.eletronicoweb.entity.Registry;

public interface RegistryService {
	
	public Registry create(Registry registry) throws Exception;
	public Registry update(Registry registry) throws Exception;
	public Optional<Registry> findById(String id);
	public void deleteById(String id);
	public Page<Registry> findPeriodByUserId(LocalDateTime startDate, LocalDateTime endDate, String userId, Pageable pages);
	public List<Registry> findPeriodByUserIdReport(LocalDateTime startDate, LocalDateTime endDate, String userId);
	
}
