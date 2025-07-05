// Package declaration: defines the package where this class resides
package com.addingdatabase.assigment_phase4;

// Import statement for Spring Boot's SpringApplication class, used to launch the application
import org.springframework.boot.SpringApplication;

// Import statement for the Spring Boot application configuration annotation
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *   Professor: Ashley Evans
 *   Student name: Minh Ngoc Tran
 *   Course: 202530-CEN-3024C-31774
 *   Date : July 5th - 2025
 *
 * AssigmentPhase4Application.java
 *
 * This is the main entry point for the Employee Tracker application.
 * It launches the Spring Boot application, initializes the application context,
 * performs component scanning, and starts the embedded web server.
 *
 * Features:
 * - Uses @SpringBootApplication annotation to enable:
 *     • Auto-configuration
 *     • Component scanning
 *     • Spring Boot configuration
 * - Contains the main() method that delegates to SpringApplication.run()
 *   to start the application.
 *
 * Technologies:
 * - Spring Boot Framework
 * - Embedded server (Tomcat by default)
 *
 */


@SpringBootApplication  // Marks this class as a Spring Boot application, enabling auto-configuration and component scanning
public class AssigmentPhase4Application {

    // Main method: the starting point of any Java application
    public static void main(String[] args) {

        // Launches the Spring Boot application by passing in this class and command-line arguments
        SpringApplication.run(AssigmentPhase4Application.class, args);
    }
}
