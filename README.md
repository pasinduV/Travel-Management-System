# Travel API Microservices

A complete microservices architecture for a travel booking system with service discovery, API gateway, and containerization.

## Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   API Gateway   │    │  Eureka Server  │    │   Hotel Service │
│   Port: 8090    │────│   Port: 8761    │────│   Port: 8082    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                        │                        │
         │                        │           ┌─────────────────┐
         └────────────────────────┼───────────│Transport Service│
                                  │           │   Port: 8083    │
                                  │           └─────────────────┘
                                  │
                            Docker Network
                         (travel-api-ds_travel-network)
```

## Services

### 🏨 **Hotel Service**

- **Port**: 8082
- **Purpose**: Manages hotel bookings and information
- **Database**: PostgreSQL (Neon Cloud)
- **Technology**: Spring Boot + JPA

### 🚗 **Transport Service**

- **Port**: 8083
- **Purpose**: Manages transportation bookings
- **Database**: PostgreSQL (Neon Cloud)
- **Technology**: Spring Boot + JPA

### 🌐 **API Gateway**

- **Port**: 8090
- **Purpose**: Routes requests to appropriate services
- **Technology**: Spring Cloud Gateway
- **Routes**:
  - `/api/hotels/**` → Hotel Service
  - `/api/transports/**` → Transport Service

### 🔍 **Eureka Server**

- **Port**: 8761
- **Purpose**: Service discovery and registration
- **Dashboard**: http://localhost:8761
- **Technology**: Netflix Eureka

## Quick Start

### Prerequisites

- Java 17+
- Maven 3.6+
- Docker & Docker Compose

### 1. Start All Services

```bash
# Option 1: Use the provided script
start-services.bat

# Option 2: Manual command
docker-compose up -d --build
```

### 2. Verify Services

```bash
# Run health checks
test-services.bat

# Or check manually
docker ps
docker-compose logs -f
```

### 3. Access Services

- **Eureka Dashboard**: http://localhost:8761
- **API Gateway**: http://localhost:8090
- **Hotel Service**: http://localhost:8082
- **Transport Service**: http://localhost:8083

## API Gateway Routes

All external requests should go through the API Gateway at `http://localhost:8090`:

### Hotel Service APIs

- `GET http://localhost:8090/api/hotels/` - Get all hotels
- `POST http://localhost:8090/api/hotels/` - Create hotel
- `GET http://localhost:8090/api/hotels/{id}` - Get hotel by ID

### Transport Service APIs

- `GET http://localhost:8090/api/transports/` - Get all transports
- `POST http://localhost:8090/api/transports/` - Create transport
- `GET http://localhost:8090/api/transports/{id}` - Get transport by ID

## API Endpoints

### 👤 User Service (Port 8085)

| Functionality | Method | Endpoint | Description |
|---------------|--------|----------|-------------|
| User Registration | POST | `/api/auth/signup` | Register a new user account |
| User Login | POST | `/api/auth/signin` | Authenticate user and get JWT token |
| Get All Users | GET | `/api/users` | Retrieve all users (Protected) |
| Create User | POST | `/api/users` | Create a new user (Protected) |
| Get User by ID | GET | `/api/users/{id}` | Get specific user by ID (Protected) |
| Update User | PUT | `/api/users/{id}` | Update user information (Protected) |
| Delete User | DELETE | `/api/users/{id}` | Delete user from system (Protected) |
| Search by Username | GET | `/api/users/search/username?username={username}` | Search users by username (Protected) |
| Search by Email | GET | `/api/users/search/email?email={email}` | Search users by email (Protected) |

**Note**: All endpoints except `/api/auth/**` require JWT token authentication.

### 🏨 Hotel Service (Port 8082)

| Functionality | Method | Endpoint | Description |
|---------------|--------|----------|-------------|
| Get All Hotels | GET | `/api/hotels` | Retrieve all available hotels |
| Create Hotel | POST | `/api/hotels` | Add a new hotel to the system |
| Get Hotel by ID | GET | `/api/hotels/{id}` | Get detailed hotel information |
| Update Hotel | PUT | `/api/hotels/{id}` | Update hotel details |
| Delete Hotel | DELETE | `/api/hotels/{id}` | Remove hotel from system |
| Get Hotels by Location | GET | `/api/hotels/location/{locationId}` | Find hotels in specific location |

### 🚗 Transport Service (Port 8083)

| Functionality | Method | Endpoint | Description |
|---------------|--------|----------|-------------|
| Get All Transports | GET | `/api/transports` | Retrieve all transport options |
| Create Transport | POST | `/api/transports` | Add new transport service |
| Get Transport by ID | GET | `/api/transports/{id}` | Get specific transport details |
| Delete Transport | DELETE | `/api/transports/{id}` | Remove transport service |
| Get Transport Courses | GET | `/api/transports/courses` | Get all available transport routes |
| Get Transports by Course | GET | `/api/transports/course/{courseId}` | Find transports for specific route |
| Create Reservation | POST | `/api/transports/reservations` | Make a transport reservation |
| Get All Reservations | GET | `/api/transports/reservations` | Retrieve all transport reservations |

### 💳 Payment Service (Port 8084)

| Functionality | Method | Endpoint | Description |
|---------------|--------|----------|-------------|
| Get All Payments | GET | `/api/payments` | Retrieve all payment records |
| Create Payment | POST | `/api/payments` | Process a new payment |
| Get Payment by ID | GET | `/api/payments/{id}` | Get specific payment details |
| Update Payment | PUT | `/api/payments/{id}` | Update payment status |
| Get Payments by User | GET | `/api/payments/user/{userId}` | Get payments for specific user |
| Get Payments by Reservation | GET | `/api/payments/reservation/{reservationId}` | Get payments for reservation |
| Get Payments by Status | GET | `/api/payments/status/{status}` | Filter payments by status (PENDING/SUCCESS/FAILED) |
| Delete Payment | DELETE | `/api/payments/{id}` | Remove payment record |

### 🌐 API Gateway Routes (Port 8090)

| Service | Route Pattern | Target Service | Description |
|---------|---------------|----------------|-------------|
| User Service | `/api/users/**` | `user-service:8085` | All user management endpoints |
| Hotel Service | `/api/hotels/**` | `hotel-service:8082` | All hotel management endpoints |
| Transport Service | `/api/transports/**` | `transport-service:8083` | All transport endpoints |
| Payment Service | `/api/payments/**` | `payment-service:8084` | All payment processing endpoints |

### 🔍 Eureka Server (Port 8761)

| Functionality | Method | Endpoint | Description |
|---------------|--------|----------|-------------|
| Eureka Dashboard | GET | `/` | Service registry web interface |
| Apps Registry | GET | `/eureka/apps` | JSON representation of all applications |
| Service Status | GET | `/eureka/apps/{appName}` | Get status of specific service |

## Technology Stack

- **Java 17**
- **Spring Boot 3.4.1**
- **Spring Cloud 2024.0.1**
- **Netflix Eureka**
- **Spring Cloud Gateway**
- **PostgreSQL**
- **Docker & Docker Compose**
- **Maven**
