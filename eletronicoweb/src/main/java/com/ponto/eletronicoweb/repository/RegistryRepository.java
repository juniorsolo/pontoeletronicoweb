package com.ponto.eletronicoweb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ponto.eletronicoweb.entity.Registry;

/**
 * 12/08/2020
 * 
 * @author junior
 *
 */
public interface RegistryRepository extends MongoRepository<Registry, String> {

}
