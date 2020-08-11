package com.ponto.eletronicoweb.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ponto.eletronicoweb.entity.Employee;
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
	
	@Autowired
	private UserServiceImpl userService;
	
	@Override
	public Subsidiary create(Subsidiary subsidiary) throws Exception{
		if(subsidiary == null || !StringUtils.isAllBlank(subsidiary.getId())) {
			throw new ServiceException("Subsidiary`s can`t be create.");
		}
		if(!subsidiary.getEmployeeList().isEmpty()) {
			for(Employee employee : subsidiary.getEmployeeList()) {
				employee.setUser(userService.create(employee.getUser()));
			}
		}
		return subsidiaryRepo.save(subsidiary);
	}
	
	@Override
	public Subsidiary update(Subsidiary subsidiary) throws Exception{
		if(subsidiary == null || StringUtils.isAllBlank(subsidiary.getId())) {
			throw new ServiceException("Can't updated subsidiary, ID is null or empty.");
		}
		Optional<Subsidiary> subsidiaryFind = findById(subsidiary.getId());
		if(!subsidiaryFind.isPresent()) {
			throw new ServiceException("Can't updated subsidiary, ID:"+ subsidiary.getId()+" not found.");
		}
		
		if(!subsidiary.getEmployeeList().isEmpty()) {
			for(Employee employee : subsidiary.getEmployeeList()) {
				employee.setUser(userService.update(employee.getUser()));
			}
		}
		subsidiaryFind.get().setName(subsidiary.getName());
		subsidiaryFind.get().setEmployeeList(subsidiary.getEmployeeList());
		
		return subsidiaryRepo.save(subsidiaryFind.get());
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
	

	
}
