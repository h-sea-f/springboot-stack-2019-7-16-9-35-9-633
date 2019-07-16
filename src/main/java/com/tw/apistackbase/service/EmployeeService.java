package com.tw.apistackbase.service;

import com.tw.apistackbase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;
}
