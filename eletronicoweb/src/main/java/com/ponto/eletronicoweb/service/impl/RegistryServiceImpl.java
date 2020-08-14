package com.ponto.eletronicoweb.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ponto.eletronicoweb.entity.Registry;
import com.ponto.eletronicoweb.repository.RegistryRepository;
import com.ponto.eletronicoweb.service.RegistryService;

/**
 *  12/08/2020
 * 
 * @author junior
 *
 */
@Service
public class RegistryServiceImpl implements RegistryService{

	@Autowired
	private RegistryRepository registryRepo;
	
	@Override
	public Registry create(Registry registry) throws Exception {
		return registryRepo.save(registry);
	}

	@Override
	public Registry update(Registry registry) throws Exception {
		return registryRepo.save(registry);
	}

	@Override
	public Optional<Registry> findById(String id) {
		return registryRepo.findById(id);
	}

	@Override
	public void deleteById(String id) {
		registryRepo.deleteById(id);
	}
	
	public Page<Registry> findPeriodByUserId(LocalDateTime stardDate, LocalDateTime endDate, String userId,  Pageable pages){
		return registryRepo.findByDateBetweenAndUserIdOrderByDate(stardDate, endDate, userId, pages);
	}

}
