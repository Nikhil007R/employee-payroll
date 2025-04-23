package com.bridgelabz.employee_payroll.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeDTO {

    @Pattern(regexp = "^[A-Z]{1}[A-Za-z\\s]{2,}$", message = "Employee name invalid, must be 3 letters and first letter should be Capital." )
    public String name;

    @Min(value = 500, message = "Min wage should be more than 500")
    public Double salary;

    @Pattern(regexp = "male|female",message = "Gender needs to be male or female")
    public String gender;

    @JsonFormat(pattern = "dd MMM yyyy")
    @NotNull(message = "Start Date cannot be null")
    @PastOrPresent(message = "Start date should be past or todays date")
    public LocalDate startDate;

    @NotBlank(message = "note cannot be empty")
    public String note;

    @NotBlank(message = "profilePic cannot be empty")
    public String profilePic;

    @NotNull(message = "Department cannot be empty")
    public List<String> departments;

    public EmployeeDTO(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }
}
