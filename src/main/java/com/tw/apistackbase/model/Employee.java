package com.tw.apistackbase.model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    private int id;
    private String name;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeRecord employeeRecord;

    public EmployeeRecord getEmployeeRecord() {
        return employeeRecord;
    }

    public void setEmployeeRecord(EmployeeRecord employeeRecord) {
        this.employeeRecord = employeeRecord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
