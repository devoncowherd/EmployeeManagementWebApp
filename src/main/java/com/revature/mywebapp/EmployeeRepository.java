package com.revature.mywebapp;

import org.springframework.data.repository.CrudRepository;


public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    public Long countById(Integer id);
}
