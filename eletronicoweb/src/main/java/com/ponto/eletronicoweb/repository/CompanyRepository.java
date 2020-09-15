package com.ponto.eletronicoweb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ponto.eletronicoweb.entity.Company;

/**
 * 
 * @author junior 16/07/2020
 *
 */
public interface CompanyRepository extends MongoRepository<Company, String>{
	
	Company findByRegisterNumber(Long registerNumber);
	
	Company findBySubsidiaryListEmployeeListDocumentNumber(Long documentNumber);
}
