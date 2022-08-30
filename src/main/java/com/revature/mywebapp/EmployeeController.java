package com.revature.mywebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public String showEmployeeList(Model model){
        List<Employee> employeeList = employeeService.listAllEmployees();
        model.addAttribute("employeeList", employeeList);
        return "employee";
    }

    @GetMapping("/employee/new")
    public String showNewEmployeeForm(Model model){
        model.addAttribute("employee", new Employee());
        return "employee_form";
    }

    @PostMapping("/employee/save")
    public String saveEmployee(Employee employee, RedirectAttributes redirectAttributes){
        employeeService.saveNewEmployee(employee);
        redirectAttributes.addFlashAttribute("message", "The employee has been saved successfully!");
        return "redirect:/employee";
    }

    @GetMapping("employee/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id,Model model, RedirectAttributes redirectAttributes){
        try {
            Employee employee = employeeService.getEmployee(id);
            model.addAttribute("employee", employee);
            model.addAttribute("pageTitle", "Update Employee - ID : " + id);
            return "redirect:/employee_form";
        } catch(EmployeeNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "Update Failed!");
            return "redirect:/employee";
        }
    }
    @DeleteMapping
    public String deleteEmployee(){
        return "redirect:/employee";
    }
}
