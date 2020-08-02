package com.ponto.eletronicoweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ponto.eletronicoweb.entity.Empresa;
import com.ponto.eletronicoweb.entity.Filial;
import com.ponto.eletronicoweb.repository.EmpresaRepository;
import com.ponto.eletronicoweb.service.EmpresaService;

@Service
public class EmpresaServiceimpl implements EmpresaService{
	
	@Autowired
	private EmpresaRepository empresaRepo;
	@Autowired
	private FilialServiceimpl filialservice;

	@Override
	public Empresa createOrUpdate(Empresa empresa) {
		if(empresa.getId() == null) {
			if(!empresa.getFiliais().isEmpty()) {
				for (Filial filial : empresa.getFiliais()) {
					empresa.getFiliais().remove(filial);
					empresa.getFiliais().add(filialservice.createOrUpdate(filial));
				}
			}
			return empresaRepo.insert(empresa);
		}else {
			if(!empresa.getFiliais().isEmpty()) {
				for (Filial filial : empresa.getFiliais()) {
					empresa.getFiliais().remove(filial);
					empresa.getFiliais().add(filialservice.createOrUpdate(filial));
				}
			}
			return empresaRepo.save(empresa);
		}
	}
		

	@Override
	public Empresa findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Empresa> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
