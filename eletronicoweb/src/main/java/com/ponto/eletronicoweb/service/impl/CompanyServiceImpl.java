package com.ponto.eletronicoweb.service.impl;

import java.util.ArrayList;
import java.util.List;
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
			throw new ServiceException("Company can't be create");
		}
		
		
		if(!company.getSubsidiaryList().isEmpty()) {
			List<Subsidiary> subsidiaryList = company.getSubsidiaryList();
			company.setSubsidiaryList(new ArrayList<>());
			
			for (Subsidiary filial : subsidiaryList) {
				company.getSubsidiaryList().add(filialservice.create(filial));
			}
		}
		
		return companyRepo.save(company);
	}
	
	@Override
	public Company update(Company company) throws Exception {
		Optional<Company> companyFinded= null;
		try {
			if(StringUtils.isAllBlank(company.getId())) {
				throw new ServiceException("Id is empty, company can't be updated");
			}
			companyFinded =  this.findById(company.getId());
			if(!companyFinded.isPresent()) {
				throw new ServiceException("Company not exist, can't be updated");
			}
			if(!company.getSubsidiaryList().isEmpty()) {
				companyFinded.get().setSubsidiaryList(new ArrayList<>());

				for (Subsidiary subsidiary : company.getSubsidiaryList()) {					
					companyFinded.get().getSubsidiaryList().add(filialservice.update(subsidiary));
				}
			}
			companyFinded.get().setRegisterNumber(company.getRegisterNumber());
			companyFinded.get().setName(company.getName());
			
			return companyRepo.save(companyFinded.get());
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
		Optional<Company> companyFinded =  this.findById(id);
		if(companyFinded.isPresent() && !companyFinded.get().getSubsidiaryList().isEmpty()) {
			for(Subsidiary sub : companyFinded.get().getSubsidiaryList()){
				filialservice.delete(sub.getId());
			}
		}
		companyRepo.deleteById(id);		
	}

	@Override
	public Iterable<Company> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return companyRepo.findAll(pageable);
	}
	
}
