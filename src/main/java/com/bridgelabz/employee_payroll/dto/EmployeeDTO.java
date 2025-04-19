package com.bridgelabz.employee_payroll.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeeDTO {

    @Pattern(regexp = "^[A-Z]{1}[A-Za-z\\s]{2,}$", message = "Employee name invalid, must be 3 letters and first letter should be Capital." )
    public String name;
    public Double salary;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Double getSalary() {
//        return salary;
//    }
//
//    public void setSalary(Double salary) {
//        this.salary = salary;
//    }
//
//    public EmployeeDTO(String name, Double salary) {
//        this.name = name;
//        this.salary = salary;
//    }
}
