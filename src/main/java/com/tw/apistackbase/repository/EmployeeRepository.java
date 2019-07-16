package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private Map<String,Employee> employees = new HashMap<>();
    public EmployeeRepository(){
        employees.put("1",new Employee(1,"Sean",22,"male",5000));
        employees.put("2",new Employee(2,"Sean1",22,"male",5000));
    }

    public List<Employee> getAllEmployee() {
        return new ArrayList<>(employees.values());
    }

    public Employee getByName(String name) {
        return employees.get(name);
    }

    public List<Employee> getEmployeesByPage(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        int end = (page - 1) * pageSize + pageSize;
        return new ArrayList<>(employees.values()).subList(start, end);
    }

    public List<Employee> getByGender(String gender) {
        return new ArrayList<>(employees.values()).stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    public void addEmployee(Employee employee) {
        employees.put(""+employee.getId(),employee);
    }

    public void updateEmployee(int id, Employee employee) {
        if(employees.containsKey(String.valueOf(id))){
            employees.remove(String.valueOf(id));
            employees.put(String.valueOf(id),employee);
        }
    }

    public void deleteEmployee(int id) {
        if(employees.containsKey(String.valueOf(id))){
            employees.remove(String.valueOf(id));
        }
    }
}
