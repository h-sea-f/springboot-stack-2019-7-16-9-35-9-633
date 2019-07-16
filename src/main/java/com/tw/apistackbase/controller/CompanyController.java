package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/companies/{companyName}")
    public Company findByName(@PathVariable String companyName) {
        return companyService.findByName(companyName);
    }

    @GetMapping("/companies/{companyName}/employees")
    public List<Employee> findEmployeesByName(@PathVariable String companyName) {
        return companyService.findEmployeesByName(companyName);
    }

    @GetMapping(params = {"page", "pageSize"}, value = "/companies")
    public List<Company> getCompaniesByPage(@RequestParam int page, @RequestParam int pageSize) {
        return companyService.getCompaniesByPage(page,pageSize);
    }
}
