
# Marvel Character REST API

## Project Description

This project is a RESTful CRUD API for Marvel characters, created for CSC 340 Software Engineering Assignment 3.

The API allows users to create, retrieve, update, delete, filter, and search Marvel characters.

It was developed using Spring Boot, Java 25, Spring Data JPA, and PostgreSQL hosted on Neon. The application is containerized using Docker and deployed on Render.

## Technologies Used

- Java 25
- Spring Boot
- Spring Data JPA
- PostgreSQL (Neon)
- Maven
- Docker
- Render

## Character Entity

The `MarvelCharacter` entity contains the following attributes:

| Attribute | Type | Rules |
|---|---|---|
| characterId | Long | Auto-generated, unique, primary key |
| name | String | Required, cannot be null or blank |
| description | String | Required, cannot be null or blank |
| universe | String | Optional |
| role | String | Optional |
| powerLevel | Integer | Optional |

The entity is stored in the `characters` PostgreSQL table.

## API Endpoints

Base path: `/api/characters`

| Method | Endpoint | Description | Success Status |
|---|---|---|---|
| GET | /api/characters | Get all characters | 200 |
| GET | /api/characters/{id} | Get character by ID | 200 |
| POST | /api/characters | Create a character | 201 |
| PUT | /api/characters/{id} | Update a character | 200 |
| DELETE | /api/characters/{id} | Delete a character | 204 |
| GET | /api/characters?universe=Marvel | Filter by universe | 200 |
| GET | /api/characters?name=iron | Search by name | 200 |

### Example POST Request

**POST** `/api/characters`

```json
{
  "name": "Iron Man",
  "description": "Tony Stark is a superhero who uses advanced armor.",
  "universe": "Marvel",
  "role": "Hero",
  "powerLevel": 95
}
```

### Example PUT Request

**PUT** `/api/characters/1`

```json
{
  "name": "Iron Man",
  "description": "Tony Stark is a genius inventor.",
  "universe": "Marvel",
  "role": "Hero",
  "powerLevel": 100
}
```

## Error Handling

- **400 Bad Request:** Returned when a POST or PUT request has a missing or blank name or description.
- **404 Not Found:** Returned when the requested character ID does not exist.

## Database Configuration

The application uses Neon PostgreSQL for persistent storage.

Database credentials are configured using the `SPRING_DATASOURCE_URL` environment variable rather than being hardcoded.

Hibernate automatically creates or updates database tables using `spring.jpa.hibernate.ddl-auto=update`.

## Deployment

The application is deployed on Render using a multi-stage Dockerfile.

**Live API:**

https://csc340-assignment3-character-api.onrender.com/api/characters

**Note:** Render's free services sleep when idle. The first request after inactivity may take up to a minute to respond.

## Running Locally

Set the `SPRING_DATASOURCE_URL` environment variable to your own PostgreSQL JDBC connection string.

Start the application with:

```bash
./mvnw spring-boot:run
```

The API will be available at:

http://localhost:8080/api/characters

## Assignment Information

Course: CSC 340 - Software Engineering

Assignment: Assignment 3 - Character REST API
