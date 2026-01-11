# Full Stack Backend (Spring Boot)

This is the backend of a Full Stack Web Application built using **Spring Boot**.  
It provides RESTful APIs for authentication, authorization, and user management with secure JWT-based security.

## 🚀 Features
- User Registration & Login
- Password encryption using BCrypt
- JWT Token generation & validation
- Role-Based Authorization (ADMIN / USER)
- Secure REST APIs
- MySQL Database Integration

## 🛠 Tech Stack
- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- MySQL
- Hibernate / JPA

## 🔐 Security
- BCrypt password hashing
- JWT-based authentication
- Role-based authorization using:
    - `hasRole('ADMIN')`
    - `hasAnyRole('USER','ADMIN')`

## 📡 API Endpoints

### Public
- `POST /auth/register`
- `POST /auth/login`

### Admin
- `GET /admin/users`

### User & Admin
- `GET /user/{id}`
- `PUT /user/{id}`
- `DELETE /user/{id}`

## ⚙️ Setup

1. Clone:
```bash
git clone https://github.com/Rohes-git/fullstack-backend.git
