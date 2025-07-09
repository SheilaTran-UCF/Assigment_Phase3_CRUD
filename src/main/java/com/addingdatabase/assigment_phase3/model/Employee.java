package com.addingdatabase.assigment_phase3.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

/**
 * Professor: Ashley Evans
 * Student: Minh Ngoc Tran
 * Course: 202530-CEN-3024C-31774 (Summer 2025)
 * Date: July 5th, 2025
 *
 * Employee.java
 *
 * This class is a model for the Employee Tracker application.
 * It defines an employee record structure and uses Lombok and
 * Jakarta validation for code simplification and input safety.
 */

@Data
public class Employee {

    // Unique employee ID
    private Long id;

    // Full name of employee
    @NotBlank(message = "Name is required")
    private String name;

    // Job title or position
    @NotBlank(message = "Position is required")
    private String position;

    // Salary must be non-null and non-negative
    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Salary must be non-negative")
    private Double salary;

    // Hire date must be today or in the past
    @NotNull(message = "Hire date is required")
    @PastOrPresent(message = "Hire date must be in the past or today")
    private LocalDate hireDate;

    // Department name
    @NotBlank(message = "Department is required")
    private String department;

    // Employment status (active or inactive)
    @NotNull(message = "Active status is required")
    private Boolean active;

    // No-arg constructor
    public Employee() {}

    // All-args constructor
    public Employee(Long id, String name, String position, Double salary,
                    LocalDate hireDate, String department, Boolean active) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
        this.department = department;
        this.active = active;
    }
}
