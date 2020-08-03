package com.ponto.eletronicoweb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ponto.eletronicoweb.entity.Empresa;

/**
 * 
 * @author junior 16/07/2020
 *
 */
public interface EmpresaRepository extends MongoRepository<Empresa, String>{
	
	Empresa findByCnpj(Long cnpj);
}
