package com.ponto.eletronicoweb.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.ponto.eletronicoweb.entity.Registry;

/**
 * 12/08/2020
 * 
 * @author junior
 *
 */
public interface RegistryRepository extends MongoRepository<Registry, String> {
	
	Page<Registry>  findByDateBetweenAndUserIdOrderByDate(LocalDateTime startDate, LocalDateTime endDate, String userId,  Pageable pages);
	List<Registry>  findByDateBetweenAndUserIdOrderByDate(LocalDateTime startDate, LocalDateTime endDate, String userId);
	

}
