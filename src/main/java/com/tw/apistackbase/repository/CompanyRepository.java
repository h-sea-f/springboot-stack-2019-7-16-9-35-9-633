package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CompanyRepository {
    private Map<String,Company> companies=new HashMap<>();
    CompanyRepository(){
        List<Employee> employeeList=new ArrayList<>();
        employeeList.add(new Employee(1,"Sean",22,"male",5000));
        employeeList.add(new Employee(2,"Sean1",22,"male",5000));
        employeeList.add(new Employee(3,"Sean2",22,"male",5000));
       companies.put("OOCL",new Company("OOCL",50,employeeList));

    }

    public List<Company> findAll() {
        return new ArrayList<>(companies.values());
    }
}
