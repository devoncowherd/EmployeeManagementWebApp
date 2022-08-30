package com.revature.mywebapp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void testAddNewEmployee() {
        Employee employee = new Employee();
        employee.setCountry("Japan");
        employee.setEmail("kkujo@revature.com");
        employee.setGender("Female");
        employee.setName("Karen Kujo");
        Employee savedEmployee = repository.save(employee);

        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Employee> allEmployees = repository.findAll();
        Assertions.assertThat(allEmployees).hasSizeGreaterThan(0);

        for(Employee employee : allEmployees){
            System.out.println(employee.toString());
        }

    }

    @Test
    public void testUpdate(){
        Integer userId = 9;
        Optional<Employee> optionalEmployee = repository.findById(userId);
        Employee employee = optionalEmployee.get();
        employee.setCountry("JP");
        repository.save(employee);

        Employee updatedEmployee = repository.findById(userId).get();
        Assertions.assertThat(updatedEmployee.getCountry()).isEqualTo("JP");
        System.out.println(updatedEmployee.toString());
    }


    @Test
    public void testGetById(){
        Integer userId = 8;
        Optional<Employee> optionalEmployee = repository.findById(userId);
        Employee employee = optionalEmployee.get();
        Assertions.assertThat(optionalEmployee.get()).isNotNull();
        System.out.println(optionalEmployee.get().toString());
    }

    @Test
    public void testDeleteEmployeeById(){
        Integer userId = 9;
        Optional<Employee> optionalEmployee = repository.findById(userId);
        if(optionalEmployee.isPresent()){
            repository.deleteById(userId);
            Assertions.assertThat(optionalEmployee).isNotPresent();
        }
    }
}
