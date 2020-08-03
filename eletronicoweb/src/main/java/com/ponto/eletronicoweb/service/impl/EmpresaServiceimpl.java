package com.ponto.eletronicoweb.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		Empresa empresaExistente= null;
		empresaExistente =  this.findByCnpj( empresa.getCnpj());
		
		if(empresaExistente != null) {
			empresaExistente.setRazaoSocial(empresa.getRazaoSocial());
			empresaExistente.setFiliais(new ArrayList<>());
			if(!empresa.getFiliais().isEmpty()) {
				for (Filial filial : empresa.getFiliais()) {
					if(empresaExistente.getFiliais().stream().filter(f -> f.getNome().equalsIgnoreCase(filial.getNome())).findAny().orElse(null) == null) {
						empresaExistente.getFiliais().add(filialservice.createOrUpdate(filial));
					}
				}
			}
			return empresaRepo.save(empresaExistente);
		}else {
		
			if(!empresa.getFiliais().isEmpty()) {
				for (Filial filial : empresa.getFiliais()) {
					empresa.getFiliais().remove(filial);
					empresa.getFiliais().add(filialservice.createOrUpdate(filial));
				}
			}
		}
		return empresaRepo.save(empresa);
	}
		

	@Override
	public Empresa findById(String id) {
		return empresaRepo.findById(id).orElse(null);
	}

	@Override
	public Empresa findByCnpj(Long cnpj) {
		Empresa empresa = empresaRepo.findByCnpj(cnpj);
		return empresa;
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
