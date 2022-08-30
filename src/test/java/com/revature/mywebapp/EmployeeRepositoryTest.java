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
        EmployeeEntity employee = new EmployeeEntity();
        employee.setCountry("Japan");
        employee.setEmail("kkujo@revature.com");
        employee.setGender("Female");
        employee.setName("Karen Kujo");
        EmployeeEntity savedEmployee = repository.save(employee);

        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<EmployeeEntity> allEmployees = repository.findAll();
        Assertions.assertThat(allEmployees).hasSizeGreaterThan(0);

        for(EmployeeEntity employee : allEmployees){
            System.out.println(employee.toString());
        }

    }

    @Test
    public void testUpdate(){
        Integer userId = 9;
        Optional<EmployeeEntity> optionalEmployee = repository.findById(userId);
        EmployeeEntity employee = optionalEmployee.get();
        employee.setCountry("JP");
        repository.save(employee);

        EmployeeEntity updatedEmployee = repository.findById(userId).get();
        Assertions.assertThat(updatedEmployee.getCountry()).isEqualTo("JP");
        System.out.println(updatedEmployee.toString());
    }


    @Test
    public void testGetById(){
        Integer userId = 8;
        Optional<EmployeeEntity> optionalEmployee = repository.findById(userId);
        EmployeeEntity employee = optionalEmployee.get();
        Assertions.assertThat(optionalEmployee.get()).isNotNull();
        System.out.println(optionalEmployee.get().toString());
    }

    @Test
    public void testDeleteEmployeeById(){
        Integer userId = 9;
        Optional<EmployeeEntity> optionalEmployee = repository.findById(userId);
        if(optionalEmployee.isPresent()){
            repository.deleteById(userId);
            Assertions.assertThat(optionalEmployee).isNotPresent();
        }
    }
}
