package com.ponto.eletronicoweb.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ponto.eletronicoweb.entity.Empresa;
import com.ponto.eletronicoweb.entity.Filial;
import com.ponto.eletronicoweb.repository.EmpresaRepository;
import com.ponto.eletronicoweb.service.EmpresaService;
import com.ponto.eletronicoweb.service.ServiceException;

@Service
public class EmpresaServiceimpl implements EmpresaService{
	
	@Autowired
	private EmpresaRepository empresaRepo;
	@Autowired
	private FilialServiceimpl filialservice;

	@Override
	public Empresa create(Empresa empresa) throws Exception {
		Empresa empresaExistente= null;
		empresaExistente =  this.findByCnpj( empresa.getCnpj());
		
		if(!StringUtils.isAllBlank(empresa.getId()) || empresaExistente != null) {
			throw new ServiceException("Empresa can't be create");
		}
		
		
		if(!empresa.getFiliais().isEmpty()) {
			for (Filial filial : empresa.getFiliais()) {
				empresa.getFiliais().remove(filial);
				empresa.getFiliais().add(filialservice.createOrUpdate(filial));
			}
		}
		
		return empresaRepo.save(empresa);
	}
	
	@Override
	public Empresa update(Empresa empresa) throws Exception {
		Optional<Empresa> empresaExistente= null;
		try {
			if(StringUtils.isAllBlank(empresa.getId())) {
				throw new ServiceException("Id is empty, empresa can't be updated");
			}
			empresaExistente =  this.findById(empresa.getId());
			if(!empresaExistente.isPresent()) {
				throw new ServiceException("Empresa not exist, can't be updated");
			}
			empresaExistente.get().setCnpj(empresa.getCnpj());
			empresaExistente.get().setRazaoSocial(empresa.getRazaoSocial());
			empresaExistente.get().setFiliais(empresa.getFiliais());
			
			return empresaRepo.save(empresaExistente.get());
		}catch (Exception e) {
			throw new ServiceException("Error: "+ e.getMessage());
		}
	}
	
	@Override
	public Optional<Empresa> findById(String id) {
		return empresaRepo.findById(id);
	}

	@Override
	public Empresa findByCnpj(Long cnpj) {
		return empresaRepo.findByCnpj(cnpj);
		 
	}
	
	@Override
	public void delete(String id) {
		empresaRepo.deleteById(id);		
	}

	@Override
	public Iterable<Empresa> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return empresaRepo.findAll(pageable);
	}
	
}
