# Marvel Character Gallery & REST API

## Project Description

This project is a full-stack Marvel Character Gallery web application developed for CSC 340 Software Engineering Assignments 3 and 4.

Assignment 3 focused on building a RESTful CRUD API for managing Marvel characters, while Assignment 4 extends the application by adding a fully functional website using Spring Boot MVC and FreeMarker.

The application allows users to:

- View Marvel characters in a gallery with images.
- Search characters by name and filter by universe.
- View detailed information about individual characters.
- Add new characters through a web form.
- Edit existing character information.
- Delete characters with confirmation.
- Access character data through REST API endpoints.

All character information is stored in a Neon PostgreSQL database.

The application is containerized using Docker and deployed on Render.

## Technologies Used

- Java 25
- Spring Boot 4
- Spring Boot MVC
- FreeMarker Template Engine
- Spring Data JPA
- Hibernate
- PostgreSQL (Neon)
- HTML5
- CSS3
- Maven
- Docker
- Render
- Git and GitHub

## Application Architecture

The application follows a layered architecture consisting of:

1. **Controller Layer:** Handles incoming HTTP requests. The REST Controller returns JSON responses, while the MVC Page Controller returns FreeMarker views.
2. **Service Layer:** Handles application logic, searching, filtering, and character management.
3. **Repository Layer:** Uses Spring Data JPA to communicate with PostgreSQL.
4. **Entity Layer:** Represents Marvel characters stored in the database.
5. **View Layer:** Uses FreeMarker templates to generate HTML pages.
6. **Database Layer:** Stores character information persistently in Neon PostgreSQL.

### MVC Request Flow

When a user visits the Marvel Gallery:

1. The browser sends an HTTP request to Spring Boot.
2. `MarvelPageController` receives the request.
3. The Controller calls `MarvelCharacterService`.
4. The Service uses `MarvelCharacterRepository` to retrieve data from Neon.
5. The Controller places the data into a Spring MVC Model.
6. FreeMarker combines the data with HTML templates.
7. The completed HTML page is returned to the browser.

The existing REST API uses the same Service, Repository, Entity, and database but returns JSON instead of HTML.

## Character Entity

The `MarvelCharacter` entity contains the following attributes:

| Attribute | Type | Rules |
|---|---|---|
| characterId | Long | Auto-generated, unique, primary key |
| name | String | Required, cannot be null or blank |
| description | String | Required, maximum database column length of 2000 |
| universe | String | Optional |
| role | String | Optional |
| powerLevel | Integer | Optional |
| imageUrl | String | Optional, stores the character image path or URL |

The entity is stored in the `characters` PostgreSQL table.

The `imageUrl` attribute was added for Assignment 4 to support character images in the gallery and details pages.

Characters without a valid image can display a placeholder image.

## Assignment 4 — Website Pages

The website uses Spring Boot MVC and FreeMarker templates.

| Method | Route | Template | Description |
|---|---|---|---|
| GET | `/` | `index.ftlh` | Displays all characters from the database |
| GET | `/?name=spider` | `index.ftlh` | Searches characters by name |
| GET | `/?universe=Marvel` | `index.ftlh` | Filters characters by universe |
| GET | `/?name=iron&universe=Marvel` | `index.ftlh` | Combines name search and universe filtering |
| GET | `/characters/{id}` | `details.ftlh` | Displays one character's complete information |
| GET | `/characters/new` | `character-form.ftlh` | Displays the Add Character form |
| POST | `/characters` | Redirect | Creates a character and returns to the gallery |
| GET | `/characters/{id}/edit` | `character-form.ftlh` | Displays a pre-filled Edit Character form |
| POST | `/characters/{id}` | Redirect | Updates an existing character |
| POST | `/characters/{id}/delete` | Redirect | Deletes a character after confirmation |
| GET | `/about` | `about.ftlh` | Explains the project and application architecture |

The application also uses:

- `layout.ftlh` for the shared navigation, page structure, and success messages.
- `error.ftlh` for friendly character-not-found pages.

### Gallery Features

The gallery displays character cards containing:

- Character image
- Name
- Universe
- Role
- Power level
- Short description
- View Details link

The gallery automatically loads characters from the Neon database rather than using hardcoded character information.

### Search Functionality

Users can search by character name, filter by universe, or combine both conditions.

Search values remain visible in the form after searching.

If no characters match, the website displays a friendly "No characters found" message.

### Character Management

The website provides forms for adding and editing Marvel characters.

After a successful operation, the application uses the Post/Redirect/Get pattern to prevent duplicate submissions when refreshing.

Success messages appear after adding, editing, or deleting a character.

Deleting a character requires user confirmation.

## Assignment 3 — REST API Endpoints

The existing REST API remains available alongside the website.

**Base path:** `/api/characters`

| Method | Endpoint | Description | Success Status |
|---|---|---|---|
| GET | `/api/characters` | Get all characters | 200 |
| GET | `/api/characters/{id}` | Get character by ID | 200 |
| POST | `/api/characters` | Create a character | 201 |
| PUT | `/api/characters/{id}` | Update a character | 200 |
| DELETE | `/api/characters/{id}` | Delete a character | 204 |
| GET | `/api/characters?universe=Marvel` | Filter by universe | 200 |
| GET | `/api/characters?name=iron` | Search by name | 200 |
| GET | `/api/characters?name=iron&universe=Marvel` | Search using both filters | 200 |

### Example POST Request

**POST** `/api/characters`

```json
{
  "name": "Iron Man",
  "description": "Tony Stark is a superhero who uses advanced armor.",
  "universe": "Marvel",
  "role": "Superhero",
  "powerLevel": 95,
  "imageUrl": "/images/iron-man.jpg"
}
```

### Example PUT Request

**PUT** `/api/characters/1`

```json
{
  "name": "Iron Man",
  "description": "Tony Stark is a genius inventor who protects the world using advanced technology.",
  "universe": "Marvel",
  "role": "Superhero",
  "powerLevel": 100,
  "imageUrl": "/images/iron-man.jpg"
}
```

## Error Handling

The application provides validation and error handling.

- **400 Bad Request:** Returned by the REST API when required character information is missing or invalid.
- **404 Not Found:** Returned when a requested character ID does not exist.
- **Form Validation:** The MVC application displays validation feedback when required fields are missing.
- **Friendly Error Page:** Unknown character IDs display a readable error page rather than a raw exception.
- **Image Fallback:** Characters without an available image display a placeholder.

## Database Configuration

The application uses Neon PostgreSQL for persistent character storage.

Database credentials are configured through environment variables rather than being hardcoded into the source code.

The `SPRING_DATASOURCE_URL` environment variable contains the PostgreSQL JDBC connection string.

Hibernate is configured using:

```properties
spring.jpa.hibernate.ddl-auto=update
```

This allows Hibernate to update the database schema when entity attributes change.

The database contains five initial Marvel characters with images:

1. Spider-Man
2. Iron Man
3. Thor
4. Black Panther
5. Doctor Strange

Additional characters can be added through the web application or REST API.

**Security:** Database credentials and connection passwords must never be committed to GitHub.

## Deployment

The application is deployed on Render using a multi-stage Dockerfile.

The same Spring Boot application serves both the FreeMarker website and the REST API.

### Live Website

https://csc340-assignment3-character-api.onrender.com/

### Live REST API

https://csc340-assignment3-character-api.onrender.com/api/characters

**Note:** Render's free services sleep when idle. The first request after inactivity may take approximately one minute to respond.

## Running Locally

### Prerequisites

- Java 25
- Maven (or the included Maven Wrapper)
- Access to a Neon PostgreSQL database
- Valid PostgreSQL JDBC connection URL

### Start the Application

Configure the `SPRING_DATASOURCE_URL` environment variable with your Neon JDBC connection string.

On macOS using the Zsh terminal, you can enter the URL without displaying it:

```bash
read -rs "SPRING_DATASOURCE_URL?Paste Neon JDBC URL: "
echo
export SPRING_DATASOURCE_URL
```

Then start Spring Boot:

```bash
./mvnw spring-boot:run
```

### Local URLs

**Website:**

http://localhost:8080/

**REST API:**

http://localhost:8080/api/characters

## Testing

The application should be tested locally and after deployment to Render.

### Website Tests

- Confirm all five database characters appear in the gallery.
- Verify every character image loads correctly.
- Search by name and universe individually and together.
- Confirm unmatched searches display "No characters found."
- Open each character's details page.
- Add a new character and verify it appears in the database.
- Edit a character and verify the existing record changes.
- Delete a character after confirmation.
- Verify success messages appear after data changes.
- Test an unknown character ID.
- Check the About page and shared navigation.
- Refresh after submitting a form to confirm no duplicate is created.

### REST API Tests

Confirm all existing GET, POST, PUT, and DELETE endpoints continue to work as expected.

Verify that responses return the appropriate HTTP status codes and JSON data.

## Assignment Information

**Course:** CSC 340 — Software Engineering

**Assignment 3:** Character REST API

**Assignment 4:** Character Gallery Web App

**Track Selected:** Track A — FreeMarker MVC

**Assignment 4 Objective:** Integrate the existing static Marvel Character Gallery with the Spring Boot backend and Neon PostgreSQL database to create a functional database-driven web application.