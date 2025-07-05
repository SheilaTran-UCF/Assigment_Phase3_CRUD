// Package declaration: specifies this class is part of the model package
package com.addingdatabase.assigment_phase4.model;

// Import statements for required libraries
import lombok.Data;             // Lombok annotation to auto-generate boilerplate code (getters, setters, toString, etc.)
import java.time.LocalDate;     // Import for handling date fields

/**
 *   Professor: Ashley Evans
 *   Student name: Minh Ngoc Tran
 *   Course: 202530-CEN-3024C-31774
 *   Date : July 5th - 2025
 *
 * Employee.java
 *
 * This is a model (data entity) class for the Employee Tracker application.
 * It represents the structure of an employee record with fields for ID, name,
 * position, salary, hire date, department, and employment status.
 *
 * Features:
 * - Uses Lombok's @Data annotation to automatically generate getters, setters,
 *   toString(), equals(), and hashCode() methods.
 * - Provides a no-argument constructor for framework compatibility.
 * - Includes an all-arguments constructor for convenient object creation.
 * - Custom toString() method for easy logging and debugging.
 *
 * Technologies:
 * - Lombok for reducing boilerplate code.
 * - Java LocalDate for handling date values.
 *
 * This class acts as a Plain Old Java Object (POJO) and is used to transfer
 * employee data between the service layer, controller, and views in the application.
 *
 */


@Data   // Lombok annotation to automatically generate getters, setters, toString(), equals(), and hashCode()
public class Employee {

    // Employee ID (unique identifier)
    private Long id;

    // Employee's full name
    private String name;

    // Employee's job position/title
    private String position;

    // Employee's salary
    private Double salary;

    // Date the employee was hired
    private LocalDate hireDate;

    // Employee's department name
    private String department;

    // Employment status (true = active, false = inactive)
    private Boolean active;

    // No-argument constructor (required for frameworks like Spring and Jackson)
    public Employee() {}

    // All-arguments constructor to create a fully initialized Employee object
    public Employee(Long id, String name, String position, Double salary, LocalDate hireDate, String department, Boolean active) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
        this.department = department;
        this.active = active;
    }

    // Getter for employee ID
    public Long getId() {
        return id;
    }

    // Setter for employee ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for employee name
    public String getName() {
        return name;
    }

    // Setter for employee name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for employee position
    public String getPosition() {
        return position;
    }

    // Setter for employee position
    public void setPosition(String position) {
        this.position = position;
    }

    // Getter for employee salary
    public Double getSalary() {
        return salary;
    }

    // Setter for employee salary
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    // Getter for employee hire date
    public LocalDate getHireDate() {
        return hireDate;
    }

    // Setter for employee hire date
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    // Getter for employee department
    public String getDepartment() {
        return department;
    }

    // Setter for employee department
    public void setDepartment(String department) {
        this.department = department;
    }

    // Getter for employee active status
    public Boolean getActive() {
        return active;
    }

    // Setter for employee active status
    public void setActive(Boolean active) {
        this.active = active;
    }

    // toString() method to provide a readable string representation of an Employee object (useful for debugging/logging)
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", department='" + department + '\'' +
                ", active=" + active +
                '}';
    }
}
