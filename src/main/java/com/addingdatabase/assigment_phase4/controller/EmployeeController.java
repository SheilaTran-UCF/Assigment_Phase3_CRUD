// Package declaration: specifies the package for this class
package com.addingdatabase.assigment_phase4.controller;

/**
 *   Professor: Ashley Evans
 *   Student name: Minh Ngoc Tran
 *   Course: 202530-CEN-3024C-31774
 *   Date : July 5th - 2025
 *
 * EmployeeController.java
 *
 * This controller class handles all web request mappings for managing employee data
 * in the Employee Tracker application. It acts as an intermediary between the
 * user interface (Thymeleaf templates) and the business logic layer (EmployeeService).
 *
 * Main Responsibilities:
 * - Display the home page and employee list.
 * - Provide forms for adding and updating employees.
 * - Handle adding, updating, and deleting employee records.
 * - Generate and display a tenure report that groups employees based on years of service.
 *
 * Technologies & Tools:
 * - Spring Boot MVC Framework
 * - Thymeleaf for template rendering
 * - Dependency Injection using @Autowired
 * - REST-style routing with @GetMapping, @PostMapping
 *
 */


// Import necessary classes and annotations
import com.addingdatabase.assigment_phase4.model.Employee;
import com.addingdatabase.assigment_phase4.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * This is the main controller class for handling web requests
 * related to Employee operations (CRUD and reports) in the application.
 */
@Controller  // Marks this class as a Spring MVC controller
public class EmployeeController {

    // Injects the EmployeeService bean to use its methods
    @Autowired
    private EmployeeService service;

    // Handler method for the home page
    @GetMapping("/")
    public String index() {
        return "index";  // Returns the name of the index.html template
    }

    // Handler method to display the list of employees
    @GetMapping("/employees")
    public String listEmployees(Model model) {
        // Adds the list of employees from the service to the model
        model.addAttribute("employees", service.getAll());
        return "employees";  // Returns the name of the employees.html template
    }

    // Handler method to show the form to add a new employee
    @GetMapping("/add")
    public String showAddForm(Model model) {
        // Adds an empty Employee object to the model for form binding
        model.addAttribute("employee", new Employee());
        return "addEmployee";  // Returns the name of the addEmployee.html template
    }

    // Handler method to process the form submission for adding a new employee
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        // Calls the service to add the new employee to the list
        service.add(employee);
        return "redirect:/employees";  // Redirects to the employee list page
    }

    // Handler method to show the form for updating an existing employee
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        // Adds the employee retrieved by ID to the model for form binding
        model.addAttribute("employee", service.findById(id));
        return "updateEmployee";  // Returns the name of the updateEmployee.html template
    }

    // Handler method to process the update form submission
    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee) {
        // Calls the service to update the employee's details
        service.update(employee);
        return "redirect:/employees";  // Redirects to the employee list page
    }

    // Handler method to delete an employee by ID
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        // Calls the service to delete the employee
        service.delete(id);
        return "redirect:/employees";  // Redirects to the employee list page
    }

    // Handler method to display the tenure report
    @GetMapping("/tenure")
    public String tenureReport(Model model) {
        // Adds the grouped tenure data to the model
        model.addAttribute("tenureGroups", service.getEmployeesGroupedByTenure());
        return "tenureReport";  // Returns the name of the tenureReport.html template
    }

}
