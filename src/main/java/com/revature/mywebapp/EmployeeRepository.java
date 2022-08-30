package com.revature.mywebapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
}
