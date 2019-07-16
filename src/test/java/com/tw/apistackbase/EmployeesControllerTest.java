package com.tw.apistackbase;

import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.controller.EmployeesController;
import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        result.andExpect(status().isOk()).andExpect(jsonPath("[0].name").value("Sean"));
    }

    @Test
    void should_return_employee_when_getByName_given_name() throws Exception {
        Employee employee=new Employee(1,"Sean",22,"male",5000);
        Mockito.when(employeeService.getByName(anyString())).thenReturn(employee);
        ResultActions result = mvc.perform(get("/employees/{name}",employee.getName()));
        result.andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Sean"));
    }
}
