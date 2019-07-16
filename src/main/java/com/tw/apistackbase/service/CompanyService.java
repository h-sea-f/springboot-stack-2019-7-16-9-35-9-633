package com.tw.apistackbase.service;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository repository;

    public List<Company> findAll() {
        return repository.findAll();
    }

    public Company findByName(String companyName) {
        return repository.findByName(companyName);
    }

    public List<Employee> findEmployeesByName(String companyName) {
        return repository.findEmployeesByName(companyName);
    }

    public List<Company> getCompaniesByPage(int page, int pageSize) {
       return repository.getCompaniesByPage(page,pageSize);
    }
}
