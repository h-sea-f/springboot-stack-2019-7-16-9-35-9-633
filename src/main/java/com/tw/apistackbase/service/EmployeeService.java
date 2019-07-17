package com.tw.apistackbase.service;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;

    public List<Employee> getAllEmployee() {
        return repository.getAllEmployee();
    }

    public Employee getById(int id) {
        return repository.getByid(id);
    }

    public List<Employee> getEmployeesByPage(int page, int pageSize) {
        return repository.getEmployeesByPage(page,pageSize);
    }

    public List<Employee> getByGender(String gender) {
        return repository.getByGender(gender);
    }

    public void addEmployee(Employee employee) {
        repository.addEmployee(employee);
    }

    public void updateEmployee(int id, Employee employee) {
        repository.updateEmployee(id,employee);
    }

    public void deleteEmployee(int id) {
        repository.deleteEmployee(id);
    }
}
