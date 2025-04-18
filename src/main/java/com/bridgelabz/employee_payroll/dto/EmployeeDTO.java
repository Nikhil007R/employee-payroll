package com.bridgelabz.employee_payroll.dto;

public class EmployeeDTO {

    public String name;
    public Double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public EmployeeDTO(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }
}
