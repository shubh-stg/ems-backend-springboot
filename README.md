# EMS Backend - Spring Boot

This is the backend REST API for the Employee Management System (EMS), developed using Spring Boot. It handles all business logic and CRUD operations for managing employee data.

## 🛠 Tech Stack
- Java 17+
- Spring Boot
- Spring Data JPA
- Maven
- MySQL or H2 Database

## ✨ Features
- Create, Read, Update, Delete (CRUD) operations
- RESTful APIs
- DTO and Mapper pattern for clean separation
- Layered architecture (Controller → Service → Repository)
- CORS enabled for frontend communication

## 📦 API Endpoints
- `GET /api/employees` – Get all employees
- `GET /api/employees/{id}` – Get employee by ID
- `POST /api/employees` – Add new employee
- `PUT /api/employees/{id}` – Update employee
- `DELETE /api/employees/{id}` – Delete employee

## 🧱 Project Structure

src/
├── controller/ # REST controllers
├── service/ # Service layer
├── repository/ # JPA repositories
├── model/ # Domain models/entities
├── dto/ # Data Transfer Objects
├── mapper/ # DTO to Entity mappers
├── exception/ # Custom exception handling

## 🚀 Upcoming Features
- JWT-based authentication
- Role-based authorization
