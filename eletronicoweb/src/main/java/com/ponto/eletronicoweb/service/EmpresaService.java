package com.ponto.eletronicoweb.service;

import java.util.Optional;

import com.ponto.eletronicoweb.entity.Empresa;

public interface EmpresaService {
	public Empresa create(Empresa empresa) throws Exception;
	public Empresa update(Empresa empresa) throws Exception;
	public Optional<Empresa> findById(String id);
	public Empresa findByCnpj(Long cnpj);
	public void delete(String id);
	public Iterable<Empresa> findAll(int page, int size);
}
