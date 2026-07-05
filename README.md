# Task Management API

Simple Spring Boot REST API for managing tasks in memory.

## Project Overview

This project implements a RESTful task management service with CRUD endpoints and a completion action. It uses an in-memory repository so it can run without a database.

## Features

- Create, read, update, delete, and complete tasks
- Basic input validation
- Global error handling
- Basic authentication
- Unit tests for the service layer

## Tech Stack

- Java 17
- Spring Boot 3.5.0
- Spring Web
- Spring Validation
- Spring Security
- Maven
- Lombok

## Project Structure

- `src/main/java/com/wellness360/taskmanagement/controller`
- `src/main/java/com/wellness360/taskmanagement/service`
- `src/main/java/com/wellness360/taskmanagement/repository`
- `src/main/java/com/wellness360/taskmanagement/model`
- `src/main/java/com/wellness360/taskmanagement/dto`
- `src/main/java/com/wellness360/taskmanagement/exception`
- `src/main/java/com/wellness360/taskmanagement/config`

## API Endpoints

- `GET /tasks` - Retrieve all tasks
- `GET /tasks/{id}` - Retrieve a task by ID
- `POST /tasks` - Create a task
- `PUT /tasks/{id}` - Update a task
- `PATCH /tasks/{id}/complete` - Mark a task as completed
- `DELETE /tasks/{id}` - Delete a task

## Authentication

Basic authentication is enabled for all endpoints.

- Username: `admin`
- Password: `admin123`

## Sample Credentials

- Username: `admin`
- Password: `admin123`

## Example Request

```http
POST /tasks
```

```json
{
	"title": "Complete Assignment",
	"description": "Finish Wellness360 case study",
	"due_date": "2026-07-10",
	"status": "pending"
}
```

## Example Response

```json
{
	"id": 1,
	"title": "Complete Assignment",
	"status": "pending"
}
```

## How to Run

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The application runs on port `9999`.

## How to Test

```bash
./mvnw clean test
```

Or on Windows:

```powershell
.\mvnw.cmd clean test
```

You can also import the Postman collection from `postman/Task Management API.postman_collection.json`.

## Unit Testing

The project includes a service-layer unit test that verifies task creation and task completion behavior.

## Assumptions

- Data is stored in memory and is lost when the application stops.
- IDs are auto-generated.
- Authentication uses Basic Auth.

## Future Improvements

- Replace in-memory storage with a database
- Add Swagger/OpenAPI documentation
- Add role-based authorization
- Add pagination and filtering
- Add more controller tests