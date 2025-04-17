package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees(){
        return repository.findAll();
    }

    public Employee createEmployee(Employee employee){
        return repository.save(employee);
    }

    public Employee updateEmployee(int id, Employee employee){
        Employee emp = repository.findById(id).orElse(null);

        if(emp != null){
            emp.setName(employee.getName());
            emp.setSalary(employee.getSalary());
            return repository.save(emp);
        }
        return null;
    }

    public String deleteEmployee(int id){
        Optional<Employee> isAvailable =  repository.findById(id);
        if(isAvailable.isPresent()) {
            repository.deleteById(id);
            return "Successfully Deleted";
        }
        else{
            return "Deletion Unsuccessfull";
        }
    }
}
