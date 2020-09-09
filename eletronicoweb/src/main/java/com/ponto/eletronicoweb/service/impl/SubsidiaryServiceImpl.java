package com.ponto.eletronicoweb.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ponto.eletronicoweb.entity.Employee;
import com.ponto.eletronicoweb.entity.Subsidiary;
import com.ponto.eletronicoweb.repository.SubsidiaryRepository;
import com.ponto.eletronicoweb.service.SubsidiaryService;

/**
 * 
 * @author junior
 *
 */
@Service
public class SubsidiaryServiceImpl implements SubsidiaryService{
	
	Logger log = LoggerFactory.getLogger(SubsidiaryServiceImpl.class);
	
	@Autowired
	private SubsidiaryRepository subsidiaryRepo;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Override
	public Subsidiary create(Subsidiary subsidiary) throws Exception{
//		if(subsidiary == null || !StringUtils.isAllBlank(subsidiary.getId())) {
//			log.error("Subsidiary can`t be create. Subsidiary or Id is null.");
//			throw new ServiceException("Subsidiary can`t be create. Subsidiary is null or ID not null.");
//		}
//		if(!subsidiary.getEmployeeList().isEmpty()) {
//			for(Employee employee : subsidiary.getEmployeeList()) {
//				employee.setUser(userService.create(employee.getUser()));
//			}
//		}
		return subsidiaryRepo.save(subsidiary);
	}
	
	@Override
	public Subsidiary update(Subsidiary subsidiary) throws Exception{
//		if(subsidiary == null || StringUtils.isAllBlank(subsidiary.getId())) {
//			log.error("Can't updated subsidiary, ID is null or empty.");
//			throw new ServiceException("Can't updated subsidiary, ID is null or empty.");
//		}
//		Optional<Subsidiary> subsidiaryFind = findById(subsidiary.getId());
//		if(!subsidiaryFind.isPresent()) {
//			log.error("Can't updated subsidiary, ID:"+ subsidiary.getId()+" not found.");
//			throw new ServiceException("Can't updated subsidiary, ID:"+ subsidiary.getId()+" not found.");
//		}
//		
		if(!subsidiary.getEmployeeList().isEmpty()) {
			for(Employee employee : subsidiary.getEmployeeList()) {
				employee.setUser(userService.update(employee.getUser()));
			}
		}
//		subsidiaryFind.get().setName(subsidiary.getName());
//		subsidiaryFind.get().setEmployeeList(subsidiary.getEmployeeList());
		
		return subsidiaryRepo.save(subsidiary);
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
	public Subsidiary findEmployeeDocument(String name) throws Exception {
		return subsidiaryRepo.findByEmployeeListName(name);
	}
	

	
}
