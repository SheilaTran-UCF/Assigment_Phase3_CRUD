package com.addingdatabase.assigment_phase3.service;

/**
 * Professor: Ashley Evans
 * Student Name: Minh Ngoc Tran
 * Course: 202530-CEN-3024C-31774
 * Date: July 5th, 2025
 *
 * EmployeeService.java
 *
 * This service class handles business logic for managing employee records
 * in the Employee Tracker application.
 *
 * Responsibilities:
 * - Load employees from a file at startup
 * - Perform CRUD operations
 * - Generate tenure reports
 * - Handle batch uploads from uploaded files
 */

import com.addingdatabase.assigment_phase3.model.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    private static final String FILE_PATH = "src/main/resources/employees.txt";

    /**
     * Load employee data from file at service startup.
     */
    @PostConstruct
    public void init() {
        loadFromFile();
    }

    /**
     * Load employee records from file into the in-memory list.
     */
    public void loadFromFile() {
        employees.clear();
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                processEmployeeLine(line);
            }
        } catch (IOException e) {
            System.err.println("Could not load file: " + e.getMessage());
        }
    }

    /**
     * Parse a single line of employee data and add to the list if valid.
     *
     * @param line raw CSV line from file or upload
     */
    private void processEmployeeLine(String line) {
        String trimmed = line.trim();

        // Skip empty lines and comments
        if (trimmed.isEmpty() || trimmed.startsWith("/*") || trimmed.startsWith("//")) {
            return;
        }

        String[] parts = trimmed.split(",");
        if (parts.length != 7) {
            System.err.println("Skipping invalid line (incorrect number of fields): " + trimmed);
            return;
        }

        if (parts[0].trim().isEmpty()) {
            System.err.println("Skipping line with empty ID: " + trimmed);
            return;
        }

        try {
            Employee employee = new Employee(
                    Long.parseLong(parts[0].trim()),
                    parts[1].trim(),
                    parts[2].trim(),
                    Double.parseDouble(parts[3].trim()),
                    LocalDate.parse(parts[4].trim()),
                    parts[5].trim(),
                    Boolean.parseBoolean(parts[6].trim())
            );
            employees.add(employee);
        } catch (NumberFormatException | DateTimeParseException ex) {
            System.err.println("Skipping line due to parsing error: " + trimmed);
            System.err.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Return list of all employees.
     *
     * @return list of Employee
     */
    public List<Employee> getAll() {
        return employees;
    }

    /**
     * Add a new employee with generated ID.
     *
     * @param employee new employee to add
     */
    public void add(Employee employee) {
        employee.setId(getNextId());
        employees.add(employee);
    }

    /**
     * Find employee by ID.
     *
     * @param id employee ID
     * @return Employee or null if not found
     */
    public Employee findById(Long id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Update existing employee details.
     *
     * @param updated employee data with ID
     */
    public void update(Employee updated) {
        Employee existing = findById(updated.getId());
        if (existing != null) {
            existing.setName(updated.getName());
            existing.setPosition(updated.getPosition());
            existing.setSalary(updated.getSalary());
            existing.setHireDate(updated.getHireDate());
            existing.setDepartment(updated.getDepartment());
            existing.setActive(updated.getActive());
        }
    }

    /**
     * Delete employee by ID.
     *
     * @param id employee ID
     */
    public void delete(Long id) {
        employees.removeIf(e -> e.getId().equals(id));
    }

    /**
     * Group employees by tenure ranges.
     *
     * @return map of tenure group name to list of employees
     */
    public Map<String, List<Employee>> getEmployeesGroupedByTenure() {
        LocalDate today = LocalDate.now();

        return employees.stream()
                .collect(Collectors.groupingBy(e -> {
                    long years = today.getYear() - e.getHireDate().getYear();
                    if (years < 1) return "0–1 years";
                    else if (years <= 5) return "1–5 years";
                    else return "5+ years";
                }));
    }

    /**
     * Generate tenure report counts.
     *
     * @return map of tenure group to count
     */
    public Map<String, Long> generateTenureReport() {
        LocalDate today = LocalDate.now();

        return employees.stream()
                .collect(Collectors.groupingBy(e -> {
                    long years = today.getYear() - e.getHireDate().getYear();
                    if (years < 1) return "0–1 years";
                    else if (years <= 5) return "1–5 years";
                    else return "5+ years";
                }, Collectors.counting()));
    }

    /**
     * Process batch upload from file and add employees.
     *
     * @param file uploaded multipart file
     * @return message indicating success or failure
     */
    public String batchUpload(MultipartFile file) {
        if (file.isEmpty()) {
            return "No file selected for upload.";
        }

        int addedCount = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("/*") || trimmed.startsWith("//")) {
                    continue;
                }

                int prevSize = employees.size();
                processEmployeeLine(trimmed);

                // If size increased, a new employee was added
                if (employees.size() > prevSize) {
                    addedCount++;
                }
            }
            return addedCount + " employee records processed successfully.";
        } catch (IOException e) {
            return "Failed to process file: " + e.getMessage();
        }
    }

    /**
     * Generate the next unique employee ID.
     *
     * @return next ID value
     */
    private Long getNextId() {
        return employees.stream()
                .mapToLong(Employee::getId)
                .max()
                .orElse(0) + 1;
    }
}
