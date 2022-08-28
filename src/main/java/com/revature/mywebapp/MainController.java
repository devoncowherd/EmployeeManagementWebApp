package com.revature.mywebapp;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {
    @GetMapping("")
    public String showHomepage(){
        return "index";
    }

    @GetMapping("")
    public String showEmployeePage(){
        return "employee";
    }
}
