package com.ponto.eletronicoweb.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ponto.eletronicoweb.entity.Company;
import com.ponto.eletronicoweb.entity.Employee;
import com.ponto.eletronicoweb.entity.Subsidiary;
import com.ponto.eletronicoweb.entity.User;
import com.ponto.eletronicoweb.repository.CompanyRepository;
import com.ponto.eletronicoweb.service.CompanyService;
import com.ponto.eletronicoweb.service.ServiceException;
import com.ponto.eletronicoweb.service.UserService;

/**
 * 
 * @author junior
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService{
	
	Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Company create(Company company) throws Exception {
		
		Company companyExist= null;
		
		if(!StringUtils.isAllBlank(company.getId())) {
			log.error("Company can't be create, id null");
			throw new ServiceException("Company can't be create");
		}
		
		companyExist =  this.findByRegisterNumber((company.getRegisterNumber()));
		
		if(companyExist != null) {
			log.error("Company can't be create, alreay exists.");
			throw new ServiceException("Company can't be create, alreay exists.");
		}
		
		if(!company.getSubsidiaryList().isEmpty()) {
					
			for (Subsidiary subsidiary : company.getSubsidiaryList()) {
				
				if(!subsidiary.getEmployeeList().isEmpty()) {
					for (Employee employee : subsidiary.getEmployeeList()) {
						Optional<User> userFinded= userService.findByLogin(employee.getUser().getLogin());
						if(userFinded.isPresent()) {
							userFinded.get().setPassword(employee.getUser().getPassword());
							userFinded.get().setProfile(employee.getUser().getProfile());
							employee.setUser(userFinded.get());
						}else {
							userService.create(employee.getUser());
						}
					}
				}
				
			}
		}
		
		return companyRepo.save(company);
	}
	
	@Override
	public Company update(Company company) throws Exception {
		Optional<Company> companyFinded= null;
		try {
			if(StringUtils.isAllBlank(company.getId())) {
				log.error("Id is empty, company can't be updated.");
				throw new ServiceException("Id is empty, company can't be updated");
			}
			companyFinded =  this.findById(company.getId());
			if(!companyFinded.isPresent()) {
				log.error("Company not exist, can't be updated.");
				throw new ServiceException("Company not exist, can't be updated");
			}
			if(!company.getSubsidiaryList().isEmpty()) {
				
				for (Subsidiary subsidiary : company.getSubsidiaryList()) {
					
					if(!subsidiary.getEmployeeList().isEmpty()) {
						for (Employee employee : subsidiary.getEmployeeList()) {
							//Create or updated user.
							Optional<User> userFinded= userService.findByLogin(employee.getUser().getLogin());
							if(userFinded.isPresent()) {
								userFinded.get().setPassword(employee.getUser().getPassword());
								userFinded.get().setProfile(employee.getUser().getProfile());
								userService.update(userFinded.get());
							}else if (employee.getUser() != null){
								userService.create(employee.getUser());
							}
						}
					}
					
				}
			}
			companyFinded.get().setRegisterNumber(company.getRegisterNumber());
			companyFinded.get().setName(company.getName());
			companyFinded.get().setAddress(company.getAddress());
			companyFinded.get().setSubsidiaryList(company.getSubsidiaryList());
			
			return companyRepo.save(companyFinded.get());
		}catch (Exception e) {
			log.error("Error from update. " + e.getMessage());
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
	public Company findByUserLogin(Long login) {
		Company company = companyRepo.findBySubsidiaryListEmployeeListDocumentNumber(login);
		
		if(company == null) {
			return null;
		}
		
		for (Subsidiary subsi : company.getSubsidiaryList()) {
		   Optional<Employee> em = subsi.getEmployeeList().stream().filter(e -> e.getDocumentNumber().equals(login)).findFirst();
		   if(em.isPresent()) {
			 subsi.setEmployeeList(new ArrayList<>());
			 subsi.getEmployeeList().add(em.get());
			 company.getSubsidiaryList().removeIf(s -> !s.getName().equals(subsi.getName()));
			 break;
		   }
		}
		
		return company;
		
	}
	
	@Override
	public void delete(String id) {
		Optional<Company> companyFinded =  this.findById(id);
		if(!companyFinded.isPresent()) {
			log.error("Company id:"+ id +" not exist, can't be deleted.");
		}

		companyRepo.deleteById(id);		
	}

	@Override
	public Iterable<Company> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return companyRepo.findAll(pageable);
	}
	
}
