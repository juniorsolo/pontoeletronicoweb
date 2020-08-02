package com.ponto.eletronicoweb.service;

import com.ponto.eletronicoweb.entity.Filial;

public interface FilialService {
	public Filial createOrUpdate(Filial filial);
	public Filial findById(String id);
	public void delete(String id);
	public Iterable<Filial> findAll();
}
