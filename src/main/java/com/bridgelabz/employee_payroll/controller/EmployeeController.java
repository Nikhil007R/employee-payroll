package com.bridgelabz.employee_payroll.controller;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;

import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.service.IEmployeeService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("employeepayrollservice")
public class EmployeeController {

    @Autowired
    private IEmployeeService service;

    @GetMapping("/get")
    public List<Employee> getAllEmployees(){
        return service.getAllEmployees();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id){
        return service.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDTO employee){
        return service.createEmployee(employee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateEmployee(@PathVariable int id,@Valid @RequestBody EmployeeDTO employee){
//        if(service.updateEmployee(id, employee) != null){
//            return "Updated Successfully";
//        }
//        return "Not Updated";
        return service.updateEmployee2(id, employee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id){
        return service.deleteEmployee(id);
    }

}
