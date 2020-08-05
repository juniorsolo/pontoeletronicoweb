package com.ponto.eletronicoweb.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ponto.eletronicoweb.entity.Subsidiary;
import com.ponto.eletronicoweb.repository.SubsidiaryRepository;
import com.ponto.eletronicoweb.service.ServiceException;
import com.ponto.eletronicoweb.service.SubsidiaryService;

/**
 * 
 * @author junior
 *
 */
@Service
public class SubsidiaryServiceImpl implements SubsidiaryService{
	
	@Autowired
	private SubsidiaryRepository subsidiaryRepo;
	
	@Override
	public Subsidiary create(Subsidiary filial) throws Exception{
		if(filial == null || StringUtils.isAllBlank(filial.getId())) {
			throw new ServiceException("Subsidiary`s can`t be create.");
		}
	
		return subsidiaryRepo.save(filial);
	}
	
	@Override
	public Subsidiary update(Subsidiary filial) throws Exception{
		if(StringUtils.isAllBlank(filial.getId())) {
			throw new ServiceException("Can't updated subsidiary, ID is null or empty.");
		}
		return subsidiaryRepo.save(filial);
	}
	
	@Override
	public Optional<Subsidiary> findById(String id) {
		return subsidiaryRepo.findById(id);
	}

	@Override
	public void delete(String id) {		
		subsidiaryRepo.deleteById(id);		
	}

	@Override
	public Iterable<Subsidiary> findAll() {		
		return subsidiaryRepo.findAll();
	}

	@Override
	public Iterable<Subsidiary> findByCompanyById(String id) {
		return subsidiaryRepo.findByCompanyId(id);
	}
	

	
}
