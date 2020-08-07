package com.ponto.eletronicoweb.service;

import java.util.Optional;

import com.ponto.eletronicoweb.entity.Subsidiary;

public interface SubsidiaryService {
	public Subsidiary create(Subsidiary filial) throws Exception;
	public Subsidiary update(Subsidiary filial) throws Exception;
	public Optional<Subsidiary> findById(String id);
	public void delete(String id);
	public Iterable<Subsidiary> findAll();
}
