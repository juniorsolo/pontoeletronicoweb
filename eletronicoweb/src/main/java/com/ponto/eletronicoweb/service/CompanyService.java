package com.ponto.eletronicoweb.service;

import java.util.Optional;

import com.ponto.eletronicoweb.entity.Company;

public interface CompanyService {
	public Company create(Company empresa) throws Exception;
	public Company update(Company empresa) throws Exception;
	public Optional<Company> findById(String id);
	public Company findByRegisterNumber(Long registerNumber);
	public void delete(String id);
	public Iterable<Company> findAll(int page, int size);
}
