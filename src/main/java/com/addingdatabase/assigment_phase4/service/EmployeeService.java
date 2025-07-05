package com.addingdatabase.assigment_phase4.service;

import com.addingdatabase.assigment_phase4.model.Employee;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 *   Professor: Ashley Evans
 *   Student name: Minh Ngoc Tran
 *   Course: 202530-CEN-3024C-31774
 *   Date : July 5th - 2025
 *
 * EmployeeService.java
 *
 * This service class handles the business logic for managing employee data
 * in the Employee Tracker application. It provides methods to perform core
 * operations such as loading employee data from a file, managing employees,
 * and generating reports.
 *
 */

@Service
public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();
    private final String FILE_PATH = "src/main/resources/employees.txt";

    @PostConstruct
    public void init() {
        loadFromFile();
    }

    public void loadFromFile() {
        employees.clear();
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                line = line.trim();

                // Skip empty lines or comment lines
                if (line.isEmpty() || line.startsWith("/*") || line.startsWith("//")) {
                    continue;
                }

                String[] parts = line.split(",");

                // Validate number of fields
                if (parts.length != 7) {
                    System.out.println("Skipping invalid line (wrong number of fields): " + line);
                    continue;
                }

                // Validate ID field is not empty
                if (parts[0].trim().isEmpty()) {
                    System.out.println("Skipping line with empty ID: " + line);
                    continue;
                }

                try {
                    Employee e = new Employee(
                            Long.parseLong(parts[0].trim()),
                            parts[1].trim(),
                            parts[2].trim(),
                            Double.parseDouble(parts[3].trim()),
                            LocalDate.parse(parts[4].trim()),
                            parts[5].trim(),
                            Boolean.parseBoolean(parts[6].trim())
                    );

                    employees.add(e);

                } catch (NumberFormatException | DateTimeParseException ex) {
                    System.out.println("Skipping line due to parsing error: " + line);
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load file: " + e.getMessage());
        }
    }

    public List<Employee> getAll() {
        return employees;
    }

    public void add(Employee employee) {
        employee.setId(getNextId());
        employees.add(employee);
    }

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

    public Employee findById(Long id) {
        return employees.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }

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

    public void delete(Long id) {
        employees.removeIf(e -> e.getId().equals(id));
    }

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

    private Long getNextId() {
        return employees.stream().mapToLong(Employee::getId).max().orElse(0) + 1;
    }
}
