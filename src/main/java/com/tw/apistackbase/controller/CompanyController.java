package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Company> getCompaniesByPage(@RequestParam(name="page") Integer page, @RequestParam Integer pageSize) {
        return companyService.getCompaniesByPage(page,pageSize);
    }

    @PostMapping("/companies")
    public void addCompany(@RequestBody Company company){
        companyService.addCompany(company);
    }

    @PutMapping("/companies/{companyName}")
    public void updateCompany(@PathVariable String companyName,@RequestBody Company company){
        companyService.updateCompany(companyName,company);
    }

    @DeleteMapping("/companies/{companyName}")
    public void deleteCompanyByName(@PathVariable String companyName){
        companyService.deleteCompanyByName(companyName);
    }
}
