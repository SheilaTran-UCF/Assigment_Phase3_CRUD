package com.addingdatabase.assigment_phase3.controller;

/**
 * Professor: Ashley Evans
 * Student Name: Minh Ngoc Tran
 * Course: 202530-CEN-3024C-31774
 * Date: July 5th, 2025
 *
 * EmployeeController.java
 *
 * Controller class managing web requests related to employee operations.
 * Acts as a bridge between the frontend UI (Thymeleaf) and business logic (EmployeeService).
 *
 * Features:
 * - Display home page and employee list
 * - Add, update, delete employee records
 * - Show employee tenure report
 * - Provide batch upload functionality for employees
 *
 * Technologies used:
 * - Spring Boot MVC Framework
 * - Thymeleaf for view rendering
 * - Dependency injection via @Autowired
 * - RESTful route mappings
 */

import com.addingdatabase.assigment_phase3.model.Employee;
import com.addingdatabase.assigment_phase3.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    /**
     * Display the home page.
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Display the list of all employees.
     */
    @GetMapping("/employees")
    public String listEmployees(Model model) {
        model.addAttribute("employees", service.getAll());
        return "employees";
    }

    /**
     * Show the form for adding a new employee.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }

    /**
     * Process the submission of the new employee form.
     * Performs validation and either shows the form with errors or redirects on success.
     */
    @PostMapping("/add")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employee,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "addEmployee";
        }
        service.add(employee);
        return "redirect:/employees";
    }

    /**
     * Show the form for updating an existing employee.
     * Redirects to the employee list if the employee ID is not found.
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Employee employee = service.findById(id);
        if (employee == null) {
            return "redirect:/employees";
        }
        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    /**
     * Process the submission of the update employee form.
     * Performs validation and either shows the form with errors or redirects on success.
     */
    @PostMapping("/update")
    public String updateEmployee(@Valid @ModelAttribute("employee") Employee employee,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "updateEmployee";
        }
        service.update(employee);
        return "redirect:/employees";
    }

    /**
     * Delete an employee by their ID.
     * Redirects to the employee list afterward.
     */
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/employees";
    }

    /**
     * Display the employee tenure report.
     */
    @GetMapping("/tenure")
    public String tenureReport(Model model) {
        model.addAttribute("tenureGroups", service.getEmployeesGroupedByTenure());
        return "tenureReport";
    }

    /**
     * Show the batch upload form for uploading employee files.
     */
    @GetMapping("/uploadForm")
    public String showUploadForm() {
        return "uploadEmployees";
    }

    /**
     * Handle the POST request for uploading employee batch file.
     * Validates the file, processes batch upload, and handles errors gracefully.
     */
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "uploadEmployees";
        }

        try {
            String message = service.batchUpload(file);
            model.addAttribute("message", message);
            // Redirect to employees list after successful upload
            return "redirect:/employees";
        } catch (Exception e) {
            model.addAttribute("message", "Error processing file: " + e.getMessage());
            return "uploadEmployees";
        }
    }
}
