package com.ponto.eletronicoweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ponto.eletronicoweb.entity.Filial;
import com.ponto.eletronicoweb.repository.FilialRepository;
import com.ponto.eletronicoweb.service.FilialService;

@Service
public class FilialServiceimpl implements FilialService{
	
	@Autowired
	private FilialRepository filialRepo;
	
	@Override
	public Filial createOrUpdate(Filial filial) {
		if(filial.getId() == null) {
			return filialRepo.insert(filial);
		}
		return filialRepo.save(filial);
	}

	@Override
	public Filial findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Filial> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
