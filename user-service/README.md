# User Service

This is a Spring Boot microservice for managing users in the travel API distributed system.

**Service Port:** `8085`  
**Base URL:** `http://localhost:8085`

## Features

- User registration and management
- JWT-based authentication and authorization
- User profile updates
- User search functionality
- RESTful API endpoints
- PostgreSQL database integration
- Input validation
- Password encryption with BCrypt

**Quick Start:**
```bash
./mvnw spring-boot:run
# Service will be available at http://localhost:8085
```

## API Endpoints

**Base URL:** `http://localhost:8085`

### Authentication APIs

1. **User Registration** - `POST /api/auth/signup`
   - Creates a new user account with JWT authentication
   - Requires username, email, and password

2. **User Login** - `POST /api/auth/signin`
   - Authenticates user and returns JWT token
   - Requires username and password

### Core APIs (Protected - Requires JWT Token)

3. **Create User** - `POST /api/users`
   - Creates a new user with username, email, and password
   - Validates unique username and email

4. **Get All Users** - `GET /api/users`
   - Retrieves all users in the system

5. **Get User by ID** - `GET /api/users/{id}`
   - Retrieves a specific user by their ID

6. **Update User** - `PUT /api/users/{id}`
   - Updates user information (username and email)
   - Validates unique constraints

### Additional APIs

7. **Delete User** - `DELETE /api/users/{id}`
   - Deletes a user from the system

8. **Search Users by Username** - `GET /api/users/search/username?username={username}`
   - Searches users by username (case-insensitive partial match)

9. **Search Users by Email** - `GET /api/users/search/email?email={email}`
   - Searches users by email (case-insensitive partial match)

## Postman Testing Examples

### 1. User Registration
**POST** `http://localhost:8085/api/auth/signup`

Headers:
```
Content-Type: application/json
```

Body (JSON):
```json
{
  "username": "john_doe",
  "email": "john.doe@example.com",
  "password": "password123"
}
```

Expected Response:
```json
"User registered successfully!"
```

### 2. User Login
**POST** `http://localhost:8085/api/auth/signin`

Headers:
```
Content-Type: application/json
```

Body (JSON):
```json
{
  "username": "john_doe",
  "password": "password123"
}
```

Expected Response:
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "id": 1,
  "username": "john_doe",
  "email": "john.doe@example.com"
}
```

**⚠️ Important:** Copy the `accessToken` from the response to use in subsequent API calls.

### 3. Get All Users (Protected)
**GET** `http://localhost:8085/api/users`

Headers:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json
```

Expected Response:
```json
[
  {
    "idUser": 1,
    "username": "john_doe",
    "email": "john.doe@example.com",
    "createdAt": "2025-07-16T10:30:00"
  }
]
```

### 4. Create User (Protected)
**POST** `http://localhost:8085/api/users`

Headers:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json
```

Body (JSON):
```json
{
  "username": "jane_smith",
  "email": "jane.smith@example.com",
  "password": "securepass456"
}
```

Expected Response:
```json
{
  "idUser": 2,
  "username": "jane_smith",
  "email": "jane.smith@example.com",
  "createdAt": "2025-07-16T10:35:00"
}
```

### 5. Get User by ID (Protected)
**GET** `http://localhost:8085/api/users/1`

Headers:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json
```

Expected Response:
```json
{
  "idUser": 1,
  "username": "john_doe",
  "email": "john.doe@example.com",
  "createdAt": "2025-07-16T10:30:00"
}
```

### 6. Update User (Protected)
**PUT** `http://localhost:8085/api/users/1`

Headers:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json
```

Body (JSON):
```json
{
  "username": "john_updated",
  "email": "john.updated@example.com"
}
```

Expected Response:
```json
{
  "idUser": 1,
  "username": "john_updated",
  "email": "john.updated@example.com",
  "createdAt": "2025-07-16T10:30:00"
}
```

### 7. Delete User (Protected)
**DELETE** `http://localhost:8085/api/users/1`

Headers:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json
```

Expected Response:
```
204 No Content
```

### 8. Search Users by Username (Protected)
**GET** `http://localhost:8085/api/users/search/username?username=john`

Headers:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json
```

Expected Response:
```json
[
  {
    "idUser": 1,
    "username": "john_doe",
    "email": "john.doe@example.com",
    "createdAt": "2025-07-16T10:30:00"
  }
]
```

### 9. Search Users by Email (Protected)
**GET** `http://localhost:8085/api/users/search/email?email=example.com`

Headers:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json
```

Expected Response:
```json
[
  {
    "idUser": 1,
    "username": "john_doe",
    "email": "john.doe@example.com",
    "createdAt": "2025-07-16T10:30:00"
  },
  {
    "idUser": 2,
    "username": "jane_smith",
    "email": "jane.smith@example.com",
    "createdAt": "2025-07-16T10:35:00"
  }
]
```

### Error Responses

#### 401 Unauthorized (No JWT Token)
```json
{
  "timestamp": "2025-07-16T10:40:00.000+00:00",
  "status": 401,
  "error": "Unauthorized",
  "path": "/api/users"
}
```

#### 400 Bad Request (Validation Error)
```json
{
  "timestamp": "2025-07-16T10:40:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Username is required"
}
```

#### 409 Conflict (Duplicate User)
```json
"Error: Username is already taken!"
```

### Testing Flow in Postman

1. **Register a new user** using `/api/auth/signup`
2. **Login** using `/api/auth/signin` and copy the JWT token
3. **Set Authorization header** `Bearer <token>` for all subsequent requests
4. **Test protected endpoints** using the JWT token
5. **Token expires in 24 hours** - re-login to get a new token

## Database Schema

```sql
CREATE TABLE users (
    id_user SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Technologies Used

- Java 17
- Spring Boot 3.4.1
- Spring Data JPA
- Spring Web
- Spring Security
- Spring Validation
- JWT (JSON Web Tokens)
- BCrypt Password Encryption
- PostgreSQL
- Maven

## Configuration

The service runs on port `8085` and connects to PostgreSQL database. JWT configuration includes:

- JWT Secret Key for token signing
- Token expiration time (24 hours)
- BCrypt password encoding

### Application Properties
```properties
server.port=8085
spring.application.name=user-service

# JWT Configuration
app.jwtSecret=mySecretKey123456789012345678901234567890123456789012345678901234567890
app.jwtExpirationMs=86400000

# Database Configuration
spring.datasource.url=jdbc:postgresql://your-database-url
spring.datasource.username=your-username
spring.datasource.password=your-password
```

## Running the Service

1. Build the project:
   ```bash
   ./mvnw clean compile
   ```

2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

3. The service will be available at `http://localhost:8085`

## Security Features

- **JWT Authentication**: Secure token-based authentication
- **Password Encryption**: BCrypt hashing for password storage
- **Protected Endpoints**: All user management APIs require valid JWT token
- **Input Validation**: Comprehensive validation for all request parameters
- **Token Expiration**: 24-hour token validity with automatic expiration

## Docker

Build and run with Docker:

```bash
# Build the JAR file
./mvnw clean package

# Build Docker image
docker build -t user-service .

# Run the container
docker run -p 8085:8085 user-service
```

## API Documentation

For complete API documentation with request/response examples, see the Postman examples above. The service provides:

- **Authentication endpoints** for user registration and login
- **Protected user management endpoints** requiring JWT authentication
- **Comprehensive error handling** with proper HTTP status codes
- **Input validation** with detailed error messages
- **Search functionality** for finding users by username or email

## Development Notes

- All endpoints except `/api/auth/**` require JWT authentication
- JWT tokens expire after 24 hours
- Passwords are encrypted using BCrypt
- The service integrates with Eureka for service discovery
- Database schema is auto-updated using JPA/Hibernate
