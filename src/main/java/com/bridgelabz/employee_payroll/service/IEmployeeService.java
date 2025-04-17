package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.model.Employee;

import java.util.List;

public interface IEmployeeService {

    public List<Employee> getAllEmployees();

    public Employee createEmployee(Employee employee);

    public Employee updateEmployee(int id, Employee employee);

    public String deleteEmployee(int id);
}
