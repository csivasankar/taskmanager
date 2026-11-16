# Task Manager Application

A full-stack Task Manager application developed using Angular and Spring Boot.

## Technologies Used

### Frontend
- Angular
- Node v22.8.0
- TypeScript
- HTML5
- CSS3

### Backend
- Java version "17.0.8" 2023-07-18 LTS
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- H2 Database

### IDEs
- IDE: Intellij IDEA Community Edition
- Visual Studio Code 1.137.0

## Project Structure

```text
taskmanager/
├── backend/     # Spring Boot application
├── frontend/      # Angular application
├── README.md
└── .gitignore
```

## Backend Setup

1. Navigate to the backend folder:

```bash
cd backend
```

2. Run the Spring Boot application:

```bash
mvn spring-boot:run
```

Backend URL: `http://localhost:8080`

## Frontend Setup

1. Navigate to the frontend folder:

```bash
cd frontend/task-manager-ui
```

2. Install dependencies:

```bash
npm install -g @angular/cli
```
3. Start Angular:

```bash
ng serve
```

Frontend URL: `http://localhost:4200`

## Working Features

- Angular frontend application.
- Spring Boot REST API.
- Backend and frontend integration.
- Task management functionality (as implemented).
- Database persistence using Spring Data JPA.
- Error Handling
- Swagger Implementation
- Pagination

## Pending Improvements

- Authentication and authorization.
- Improved exception handling.
- Unit and integration test coverage.
- User-friendly error messages.
- JWT-based authentication.
- Role-based access control.
- Logging and monitoring.

## Author

Developed as a full-stack development project using Angular and Spring Boot.