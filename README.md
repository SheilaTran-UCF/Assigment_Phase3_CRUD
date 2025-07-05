# EmployeeTrackerDMS - Assignment Phase 3

## Project Overview
EmployeeTrackerDMS is a CRUD (Create, Read, Update, Delete) web application built with Spring Boot to manage employee records. The application allows users to add, view, update, and generate tenure reports for employees.

---

## Project Structure
Assignment_Phase3_CRUD/
├── src/
│ ├── main/
│ │ ├── java/com/addingdatabase/assignment_phase3/
│ │ │ ├── controller/ # Contains controllers for handling web requests
│ │ │ │ └── EmployeeController.java
│ │ │ ├── model/ # Contains entity/model classes
│ │ │ │ └── Employee.java
│ │ │ ├── service/ # Contains business logic services
│ │ │ │ └── EmployeeService.java
│ │ │ └── AssignmentPhase3Application.java # Main Spring Boot application class
│ │ ├── resources/
│ │ │ ├── static/ # Static files like CSS
│ │ │ │ └── style.css
│ │ │ ├── templates/ # Thymeleaf HTML templates for web views
│ │ │ │ ├── addEmployee.html
│ │ │ │ ├── employees.html
│ │ │ │ ├── index.html
│ │ │ │ ├── tenureReport.html
│ │ │ │ └── updateEmployee.html
│ │ │ ├── application.properties # Configuration file
│ │ │ └── employees.txt # Sample employee data file
│ ├── test/
│ │ └── java/com/addingdatabase/assignment_phase3/
│ │ └── AssignmentPhase3ApplicationTests.java # Unit tests
├── .gitignore
├── mvnw, mvnw.cmd # Maven wrapper files
├── pom.xml # Maven configuration file



---

## Features

- Add new employee records
- View a list of employees
- Update existing employee information
- Generate tenure report of employees
- Uses Thymeleaf for dynamic HTML rendering
- Styles provided with custom CSS (style.css)
- Data stored in `employees.txt` for persistence

---

## Requirements

- Java 17 (or compatible version)
- Maven
- Spring Boot

---

## How to Run

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd Assignment_Phase3_CRUD

2. Build the project:
./mvnw clean install
Run the Spring Boot application:

3. Run the Spring Boot application:
./mvnw spring-boot:run

4. Open a web browser and navigate to:

http://localhost:8080/














