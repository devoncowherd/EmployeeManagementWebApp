package com.revature.mywebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeEntity> listAll(){
        return (List<EmployeeEntity>) employeeRepository.findAll();
    }


}
