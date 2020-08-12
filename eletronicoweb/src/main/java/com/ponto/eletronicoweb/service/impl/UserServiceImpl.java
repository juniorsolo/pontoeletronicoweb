package com.ponto.eletronicoweb.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ponto.eletronicoweb.entity.User;
import com.ponto.eletronicoweb.repository.UserRepository;
import com.ponto.eletronicoweb.service.ServiceException;
import com.ponto.eletronicoweb.service.UserService;

/**
 * 
 * @author junior 11/08/2020
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User create(User user) throws Exception {
		
		if(user == null) {
			log.error("Not cold' be create User it is null.");
			throw new ServiceException("Not cold' be create User it is null.");
		}
		if(StringUtils.isAllBlank(user.getLogin()) || StringUtils.isAllBlank(user.getPassword())) {
			log.error("Login or password not be null.");
			throw new ServiceException("Login or password not be null.");
		}
		return userRepo.save(user);
	}

	@Override
	public User update(User user) throws Exception {
		
		if(user == null || StringUtils.isAllBlank(user.getId())) {
			log.error("Object or id not be null.");
			throw new ServiceException("Object or id not be null.");
		}
		
		if(StringUtils.isAllBlank(user.getLogin()) || StringUtils.isAllBlank(user.getPassword())) {
			log.error("Login or password not be null.");
			throw new ServiceException("Login or password not be null.");
		}
		Optional<User> userFinded = this.findById(user.getId());
		
		if(!userFinded.isPresent()) {
			log.error("User not be updated. User not found...");
			throw new ServiceException("User not be updated. User not found...");
		}
		
		userFinded.get().setLogin(user.getLogin());
		userFinded.get().setPassword(user.getPassword());
		userFinded.get().setProfile(user.getProfile());
		
		return userRepo.save(userFinded.get());
	}

	@Override
	public Optional<User> findById(String id) {
		return userRepo.findById(id);
	}

	@Override
	public void delete(String id) {
		userRepo.deleteById(id);
	}

}
