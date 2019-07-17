package com.tw.apistackbase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.controller.EmployeesController;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeesController.class)
public class EmployeesControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void should_return_employee_when_getAllEmployee() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Sean", 22, "male", 5000));
        employeeList.add(new Employee(2, "Sean1", 22, "male", 5000));
        employeeList.add(new Employee(3, "Sean2", 22, "male", 5000));
        Mockito.when(employeeService.getAllEmployee()).thenReturn(employeeList);
        ResultActions result = mvc.perform(get("/employees"));
        result.andExpect(status().isOk()).andExpect(jsonPath("[0].name").value("Sean")).
                andExpect(jsonPath("[1].name").value("Sean1")).
                andExpect(jsonPath("[2].name").value("Sean2"));
    }

    @Test
    void should_return_employee_when_getById_given_id() throws Exception {
        Employee employee = new Employee(1, "Sean", 22, "male", 5000);
        Mockito.when(employeeService.getById(anyInt())).thenReturn(employee);
        ResultActions result = mvc.perform(get("/employees/{id}", employee.getId()));
        result.andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Sean")).
                andExpect(jsonPath("$.age").value(22)).
                andExpect(jsonPath("$.gender").value("male")).
                andExpect(jsonPath("$.salary").value(5000));
    }

    @Test
    void should_return_employees_when_getEmployeesPage_give_page_pageSize() throws Exception {
        Employee employee = new Employee(1, "Sean", 22, "male", 5000);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        Mockito.when(employeeService.getEmployeesByPage(anyInt(), anyInt())).thenReturn((employees));
        ResultActions result = mvc.perform(get("/employees?page=1&pageSize=1"));
        result.andExpect(status().isOk()).andExpect(jsonPath("[0].name").value("Sean"));
    }

    @Test
    void should_create_employee() throws Exception {
        Employee employee = new Employee(4, "Sean3", 33, "male", 600);
        ResultActions result = mvc.perform(post("/employees").contentType("application/json;charset=UTF-8").
                content(new ObjectMapper().writeValueAsString(employee)));
        result.andExpect(status().isCreated());
    }
}
