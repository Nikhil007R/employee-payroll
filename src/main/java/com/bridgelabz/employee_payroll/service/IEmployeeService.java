package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEmployeeService {

    public List<Employee> getAllEmployees();

    public ResponseEntity<Employee> createEmployee(EmployeeDTO employee);

    public ResponseEntity<Employee> updateEmployee(int id, EmployeeDTO employee);

    public ResponseEntity<String> deleteEmployee(int id);

    public ResponseEntity<Employee> findById(int id);

//    public ResponseEntity<ResponseDTO> createEmployee1(EmployeeDTO employee);

    public ResponseEntity<ResponseDTO> updateEmployee2(int id, EmployeeDTO employee);
}
