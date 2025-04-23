package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.exception.EmployeeNotFoundException;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService implements IEmployeeService{

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees(){
        return repository.findAll();
    }

    public ResponseEntity<Employee> findById(int id){
        Employee employee = repository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee with " + id + " not found"));
        if(employee != null){
            log.info("Found id successfully: ");
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        log.info("did'nt found id: ");
        return new ResponseEntity<>(employee, HttpStatus.BAD_REQUEST);
//        return employee;
    }

    public ResponseEntity<Employee> createEmployee(EmployeeDTO employee){
        Employee emp = new Employee(employee);

//        emp.setName(employee.getName());
//        emp.setSalary(employee.getSalary());
//        if(emp.getName() != null && (emp.getSalary() != 0)){
//            repository.save(emp);
//            log.info("Created successfully: ");
//            return new ResponseEntity<>(emp, HttpStatus.CREATED);
//        }

//        log.info("Employee not created: ");
//        return new ResponseEntity<>(emp, HttpStatus.BAD_REQUEST);
        repository.save(emp);
        log.info("Employee created with id: {} " + emp.getEmployeeId());
        return  new ResponseEntity<>(emp, HttpStatus.CREATED);

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
            log.info("Successfully Deleted");
            return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
        }
            log.info("Deleted Unsuccessfull: ");
            return new ResponseEntity<>("Deletion Unsuccessfull", HttpStatus.BAD_REQUEST);

    }

//    public ResponseEntity<ResponseDTO> createEmployee1(EmployeeDTO employee){
//
//        Employee emp = repository.findById()
//    }
    public ResponseEntity<ResponseDTO> updateEmployee2(int id, EmployeeDTO employee){
        Employee emp = repository.findById(id).orElse(null);

        if(emp != null){
            if (employee.getName() != null) emp.setName(employee.getName());
            if (employee.getSalary() != 0) emp.setSalary(employee.getSalary());
            if (employee.getDepartments() != null) emp.setDepartments(employee.getDepartments());
            if (employee.getGender() != null) emp.setGender(employee.getGender());
            if (employee.getNote() != null) emp.setNote(employee.getNote());
            if (employee.getProfilePic() != null) emp.setProfilePic(employee.getProfilePic());
            if (employee.getStartDate() != null) emp.setStartDate(employee.getStartDate());
            log.info("Employee updated with id: {}", emp.getEmployeeId());
            repository.save(emp);

            log.info("Updated Successfully!");
            ResponseDTO response = new ResponseDTO("Employee Updated Successfully", emp);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        log.info("Unsuccessfull updation");
        ResponseDTO response = new ResponseDTO("Employee not found Successfully", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}
