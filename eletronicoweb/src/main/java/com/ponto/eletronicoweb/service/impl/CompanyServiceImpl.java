package com.ponto.eletronicoweb.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ponto.eletronicoweb.entity.Company;
import com.ponto.eletronicoweb.entity.Subsidiary;
import com.ponto.eletronicoweb.repository.CompanyRepository;
import com.ponto.eletronicoweb.service.CompanyService;
import com.ponto.eletronicoweb.service.ServiceException;

/**
 * 
 * @author junior
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private SubsidiaryServiceImpl filialservice;

	@Override
	public Company create(Company company) throws Exception {
		Company companyExist= null;
		companyExist =  this.findByRegisterNumber((company.getRegisterNumber()));
		
		if(!StringUtils.isAllBlank(company.getId()) || companyExist != null) {
			throw new ServiceException("corporation can't be create");
		}
		
		
		if(!company.getSubsidiaryList().isEmpty()) {
			for (Subsidiary filial : company.getSubsidiaryList()) {
				company.getSubsidiaryList().remove(filial);
				company.getSubsidiaryList().add(filialservice.create(filial));
			}
		}
		
		return companyRepo.save(company);
	}
	
	@Override
	public Company update(Company company) throws Exception {
		Optional<Company> empresaExistente= null;
		try {
			if(StringUtils.isAllBlank(company.getId())) {
				throw new ServiceException("Id is empty, company can't be updated");
			}
			empresaExistente =  this.findById(company.getId());
			if(!empresaExistente.isPresent()) {
				throw new ServiceException("Company not exist, can't be updated");
			}
			empresaExistente.get().setRegisterNumber(company.getRegisterNumber());
			empresaExistente.get().setName(company.getName());
			empresaExistente.get().setSubsidiaryList(company.getSubsidiaryList());
			
			return companyRepo.save(empresaExistente.get());
		}catch (Exception e) {
			throw new ServiceException("Error: "+ e.getMessage());
		}
	}
	
	@Override
	public Optional<Company> findById(String id) {
		return companyRepo.findById(id);
	}

	@Override
	public Company findByRegisterNumber(Long registerNumber) {
		return companyRepo.findByRegisterNumber(registerNumber);
	}
	
	@Override
	public void delete(String id) {
		companyRepo.deleteById(id);		
	}

	@Override
	public Iterable<Company> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return companyRepo.findAll(pageable);
	}
	
}