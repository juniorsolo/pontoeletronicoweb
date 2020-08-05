package com.ponto.eletronicoweb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ponto.eletronicoweb.entity.Subsidiary;

/**
 * 
 * @author junior 16/07/2020
 *
 */
public interface FilialRepository extends MongoRepository<Subsidiary, String> {
	
}
