package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeesController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/employees/{name}")
    public Employee getByName(@PathVariable String name) {
        return employeeService.getByName(name);
    }

    @GetMapping(params = {"page", "pageSize"}, value = "/companies")
    public List<Employee> getEmployeesByPage(@RequestParam int page, @RequestParam int pageSize) {
        return employeeService.getEmployeesByPage(page, pageSize);
    }

    @GetMapping(value = "/employees", params = {"gender"})
    public List<Employee> getByGender(@RequestParam String gender) {
        return employeeService.getByGender(gender);
    }

    @PostMapping("/employees")
    public void addEmployee(Employee employee){
        employeeService.addEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public void updateEmployee(@PathVariable int id,Employee employee){
        employeeService.updateEmployee(id,employee);
    }

    @DeleteMapping("/employees?{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }

}
