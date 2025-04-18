package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Employee> findById(int id){
        Employee employee = repository.findById(id).orElse(null);
        if(employee != null){
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(employee, HttpStatus.BAD_REQUEST);
//        return employee;
    }

    public ResponseEntity<Employee> createEmployee(EmployeeDTO employee){
        Employee emp = new Employee();

        emp.setName(employee.getName());
        emp.setSalary(employee.getSalary());
        if(emp.getName() != null && (emp.getSalary() != 0)){
            repository.save(emp);
            return new ResponseEntity<>(emp, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(emp, HttpStatus.BAD_REQUEST);
//        return repository.save(employee);
    }

    public ResponseEntity<Employee> updateEmployee(int id, EmployeeDTO employee){
        Employee emp = repository.findById(id).orElse(null);

        if(emp != null){
            emp.setName(employee.getName());
            emp.setSalary(employee.getSalary());

            repository.save(emp);
            return new ResponseEntity<>(emp, HttpStatus.OK);
        }
        return null;
    }

    public ResponseEntity<String> deleteEmployee(int id){
        Optional<Employee> isAvailable =  repository.findById(id);
        if(isAvailable.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Deletion Unsuccessfull", HttpStatus.BAD_REQUEST);
        }
    }

//    public ResponseEntity<ResponseDTO> createEmployee1(EmployeeDTO employee){
//
//        Employee emp = repository.findById()
//    }
    public ResponseEntity<ResponseDTO> updateEmployee2(int id, EmployeeDTO employee){

        Employee emp = repository.findById(id).orElse(null);

        if(emp != null){
            emp.setSalary(employee.getSalary());
            emp.setName(employee.getName());
            repository.save(emp);

            ResponseDTO response = new ResponseDTO("Employee Updated Successfully", emp);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        ResponseDTO response = new ResponseDTO("Employee not found Successfully", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}
