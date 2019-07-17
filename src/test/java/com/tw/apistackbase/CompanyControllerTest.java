package com.tw.apistackbase;


import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
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
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyService companyService;

    @Test
    void should_return_company_by_name() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Sean", 22, "male", 5000));
        employeeList.add(new Employee(2, "Sean1", 22, "male", 5000));
        employeeList.add(new Employee(3, "Sean2", 22, "male", 5000));
        Company company = new Company("OOCL", 50, employeeList);
        Mockito.when(companyService.findByName(anyString())).thenReturn(company);
        ResultActions result = mvc.perform(get("/companies/{companyName}", company.getCompanyName()));
        result.andExpect(status().isOk()).andExpect(jsonPath("$.companyName").value("OOCL")).
                andExpect(jsonPath("$.employees.[0].name").value("Sean")).
                andExpect(jsonPath("$.employees.[1].name").value("Sean1")).
                andExpect(jsonPath("$.employees.[2].name").value("Sean2"));
    }

    @Test
    void should_return_employees_by_companyName() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Sean", 22, "male", 5000));
        employeeList.add(new Employee(2, "Sean1", 22, "male", 5000));
        employeeList.add(new Employee(3, "Sean2", 22, "male", 5000));
        Company company = new Company("OOCL", 50, employeeList);
        Mockito.when(companyService.findEmployeesByName(anyString())).thenReturn(employeeList);
        ResultActions result = mvc.perform(get("/companies/{companyName}/employees", company.getCompanyName()));
        result.andExpect(status().isOk()).andExpect(jsonPath("[0].name").value("Sean")).
                andExpect(jsonPath("[0].age").value(22)).
                andExpect(jsonPath("[0].gender").value("male")).
                andExpect(jsonPath("[0].salary").value(5000)).
                andExpect(jsonPath("[1].name").value("Sean1")).
                andExpect(jsonPath("[1].age").value(22)).
                andExpect(jsonPath("[2].name").value("Sean2"));

    }

    @Test
    void should_return_companies_when_getCompaniesByPage_give_page_pageSize() throws Exception{
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Sean", 22, "male", 5000));
        employeeList.add(new Employee(2, "Sean1", 22, "male", 5000));
        employeeList.add(new Employee(3, "Sean2", 22, "male", 5000));
        Company company = new Company("OOCL", 50, employeeList);
        List<Company> companies=new ArrayList<>();
        companies.add(company);
        Mockito.when(companyService.getCompaniesByPage(anyInt(),anyInt())).thenReturn(companies);
        ResultActions result = mvc.perform(get("/companies?page=1&pageSize=1"));
        result.andExpect(status().isOk()).andExpect( jsonPath("[0].companyName").value("OOCL")).
                andExpect(jsonPath("[0].employees.[0].name").value("Sean")).
                andExpect(jsonPath("[0].employees.[1].name").value("Sean1")).
                andExpect(jsonPath("[0].employees.[2].name").value("Sean2"));
    }

    @Test
    void should_return_ok_when_addCompany_give_company() throws Exception{
        ResultActions result = mvc.perform(post("/companies").contentType("application/json;charset=UTF-8").content("{}"));
        result.andExpect(status().isOk());
    }

    @Test
    void should_return_ok_when_updateCompany_give_companyName_company() throws Exception{
        ResultActions result = mvc.perform(put("/companies/{companyName}","OOCL").contentType("application/json;charset=UTF-8").content("{}"));
        result.andExpect(status().isOk());
    }
}
