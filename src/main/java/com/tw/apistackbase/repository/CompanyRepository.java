package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CompanyRepository {
    private Map<String, Company> companies = new HashMap<>();

    CompanyRepository() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Sean", 22, "male", 5000));
        employeeList.add(new Employee(2, "Sean1", 22, "male", 5000));
        employeeList.add(new Employee(3, "Sean2", 22, "male", 5000));
        companies.put("OOCL", new Company("OOCL", 50, employeeList));

    }

    public List<Company> findAll() {
        return new ArrayList<>(companies.values());
    }

    public Company findByName(String companyName) {
        return companies.get(companyName);
    }

    public List<Employee> findEmployeesByName(String companyName) {
        return companies.get(companyName).getEmployees();
    }

    public List<Company> getCompaniesByPage(Integer page, Integer pageSize) {
        int start = (page - 1) * pageSize;
        int end = (page - 1) * pageSize + pageSize;
        return new ArrayList<>(companies.values()).subList(start, end);
    }

    public void addCompany(Company company) {
        this.companies.put(company.getCompanyName(), company);
    }

    public void updateCompany(String companyName, Company company) {
        if (companies.containsKey(companyName)) {
            this.companies.remove(companyName);
            this.companies.put(companyName, company);
        }
    }

    public void deleteCompanyByName(String companyName) {
        companies.remove(companyName);
    }
}
