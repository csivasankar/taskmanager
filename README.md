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
cd frontend
```

2. Install dependencies:

```bash
npm install -g @angular/cli
```

3. Create the project:

```
ng new task-manager-ui --standalone --routing=false --style=css
```

4. Navigate into the project:

```
cd task-manager-ui
```

5. Generate the component and service:

```
ng generate component task
ng generate service task
```

6. Create Task interface:

```
 Create this file inside src/app/.
 task.model.ts
```

7. Change Angular service

```
task.service.ts
```

8. Configure HttpClient

```
app.config.ts
```

9. Change task component

```agsl
task.component.ts
```

10. Change task HTML page

```agsl
task.component.html
```

11. Add required css changes

```agsl
task.component.css
```

12. Display task component

```agsl
app.component.ts
```

13. Start Angular:

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

## Pending Improvements

- Authentication and authorization.
- Improved exception handling.
- Unit and integration test coverage.
- User-friendly error messages.
- Swagger Implementation
- JWT-based authentication.
- Role-based access control.
- Logging and monitoring.
- Pagination

## Author

Developed as a full-stack development project using Angular and Spring Boot.