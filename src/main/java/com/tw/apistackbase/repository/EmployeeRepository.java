package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class EmployeeRepository {
    private Map<String,Employee> employees = new HashMap<>();
    public EmployeeRepository(){
        employees.put("1",new Employee(1,"Sean",22,"male",5000));
        employees.put("2",new Employee(2,"Sean1",22,"male",5000));
    }
}
