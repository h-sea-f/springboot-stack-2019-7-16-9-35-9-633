package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    @PostMapping("/companies")
    public Company create(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @GetMapping("/companies")
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @GetMapping("/companies/{id}")
    public Company findById(@PathVariable int id) {
        return companyRepository.findById(id).get();
    }

    @DeleteMapping("/companies/{id}")
    public void delete(@PathVariable int id) {
        companyRepository.deleteById(id);
    }

    @PutMapping("/companies")
    public Company update(@RequestBody Company company) {
        return companyRepository.save(company);
    }

}
