package com.ponto.eletronicoweb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ponto.eletronicoweb.entity.Employee;
/**
 * 
 * @author junior
 *
 */
public interface EmployeeRepository extends MongoRepository<Employee, String>{

}
