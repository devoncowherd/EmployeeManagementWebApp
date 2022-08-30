package com.revature.mywebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public String showEmployeeList(Model model){
        List<EmployeeEntity> employeeEntityList = employeeService.listAll();
        model.addAttribute("employeeEntityList", employeeEntityList);
        return "employee";
    }


}
