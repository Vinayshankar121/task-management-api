# Design Notes

## Architecture

The application follows a simple layered Spring Boot structure:

- Controller layer exposes REST endpoints
- Service layer contains business logic
- Repository layer stores tasks in memory
- DTOs isolate request and response payloads
- Exception handlers centralize error responses

## Design Decisions

- Kept the implementation small and easy to review for a case study.
- Used an in-memory store so the project runs without database setup.
- Separated request and response models to keep the API contract clean.

## Why `ConcurrentHashMap`

`ConcurrentHashMap` provides thread-safe access without external synchronization and is a good fit for a small in-memory store.

## Why DTOs

DTOs prevent the controller from exposing internal domain objects directly and make request validation clearer.

## Why `ResponseEntity`

`ResponseEntity` allows the API to return proper HTTP status codes such as `201 Created`, `204 No Content`, `404 Not Found`, and `400 Bad Request`.

## Why Basic Authentication

Basic auth satisfies the bonus requirement with minimal setup and keeps the project simple for a hiring exercise.

## Assumptions

- The service is for demonstration and evaluation, not production use.
- Authentication credentials are hardcoded for the exercise only.
- `due_date` is required and must not be in the past.

## Limitations

- Data is lost on restart because storage is in memory.
- There is no pagination or search support.
- Authentication is not backed by a database or user management flow.
- The API does not persist audit history.