package com.bridgelabz.employee_payroll.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

//    @Pattern(regexp = "[^[A-Z]{1}[A-Za-z\\s]{2,}$]", message = "Employee name invalid")
    private String name;
    private Double salary;

}
