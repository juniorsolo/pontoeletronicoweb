package com.ponto.eletronicoweb.service;

import com.ponto.eletronicoweb.entity.Empresa;

public interface EmpresaService {
	public Empresa createOrUpdate(Empresa empresa);
	public Empresa findById(String id);
	public Empresa findByCnpj(Long cnpj);
	public void delete(String id);
	public Iterable<Empresa> findAll(int page, int size);
}
