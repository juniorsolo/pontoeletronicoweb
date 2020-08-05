package com.ponto.eletronicoweb.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ponto.eletronicoweb.entity.Subsidiary;
import com.ponto.eletronicoweb.repository.FilialRepository;
import com.ponto.eletronicoweb.service.SubsidiaryService;

@Service
public class SubsidiaryServiceImpl implements SubsidiaryService{
	
	@Autowired
	private FilialRepository filialRepo;
	
	@Override
	public Subsidiary createOrUpdate(Subsidiary filial) {
		if(filial.getId() == null) {
			return filialRepo.insert(filial);
		}
		return filialRepo.save(filial);
	}

	@Override
	public Optional<Subsidiary> findById(String id) {
		return filialRepo.findById(id);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Subsidiary> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
