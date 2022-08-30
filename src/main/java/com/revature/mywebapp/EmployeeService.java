package com.revature.mywebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> listAllEmployees(){
        return (List<Employee>) employeeRepository.findAll();
    }

    public void saveNewEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee getEmployee(Integer employeeId) throws EmployeeNotFoundException{
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if(optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }
        throw new EmployeeNotFoundException("Could Not Find Employee With ID " + employeeId + ". \nPlease try again");
    }
    public void deleteEmployee(Employee employee){

    }


}
