# Hotel Booking API with RabbitMQ Communication

This project demonstrates microservices communication using RabbitMQ between the Hotel Service and Payment Service.

## Architecture Overview

```
Client -> Hotel Service -> RabbitMQ -> Payment Service -> RabbitMQ -> Hotel Service
```

### Services Communication Flow:

1. **Hotel Service** receives booking request
2. **Hotel Service** creates reservation and sends payment message to **RabbitMQ**
3. **Payment Service** processes payment and updates status
4. **Payment Service** sends confirmation back to **Hotel Service** via **RabbitMQ**
5. **Hotel Service** updates reservation status based on payment result

## API Endpoints

### Hotel Service (Port 8082)

#### Create Hotel Booking
```bash
POST /api/bookings/hotel
Content-Type: application/json

{
    "hotelId": 1,
    "roomId": 1,
    "userId": 123,
    "checkInDate": "2025-08-01",
    "checkOutDate": "2025-08-05",
    "guestName": "John Doe",
    "guestEmail": "john.doe@example.com",
    "guestPhone": "+1-555-0123",
    "numberOfGuests": 2
}
```

#### Confirm Booking (called by Payment Service)
```bash
PUT /api/bookings/{reservationId}/confirm
```

#### Cancel Booking (called by Payment Service)
```bash
PUT /api/bookings/{reservationId}/cancel
```

### Payment Service (Port 8084)

#### Get All Payments
```bash
GET /api/payments
```

#### Get Payment by ID
```bash
GET /api/payments/{id}
```

## RabbitMQ Configuration

### Exchanges:
- `booking.exchange` - Topic exchange for all booking-related messages

### Queues:
- `booking.queue` - For booking creation messages
- `payment.queue` - For payment processing messages
- `booking.confirmation.queue` - For booking confirmation messages

### Routing Keys:
- `booking.created` - When a new booking is created
- `payment.process` - When payment needs to be processed
- `booking.confirmed` - When booking status is updated

## Message Flow

### 1. Booking Creation Flow:
```
Hotel Service -> payment.process -> Payment Service
```

### 2. Payment Confirmation Flow:
```
Payment Service -> booking.confirmed -> Hotel Service
```

## Testing the API

### Prerequisites:
1. Start all services using Docker Compose:
   ```bash
   docker-compose up -d
   ```

2. Wait for all services to be up and registered with Eureka

### Test Steps:

1. **Create a hotel booking:**
   ```bash
   curl -X POST "http://localhost:8082/api/bookings/hotel" \
   -H "Content-Type: application/json" \
   -d '{
       "hotelId": 1,
       "roomId": 1,
       "userId": 123,
       "checkInDate": "2025-08-01",
       "checkOutDate": "2025-08-05",
       "guestName": "John Doe",
       "guestEmail": "john.doe@example.com",
       "guestPhone": "+1-555-0123",
       "numberOfGuests": 2
   }'
   ```

2. **Check payment records:**
   ```bash
   curl -X GET "http://localhost:8084/api/payments"
   ```

3. **Monitor RabbitMQ:**
   - Open http://localhost:15672
   - Login with admin/admin123
   - Check queues and messages

## Key Features Demonstrated

### 1. **Asynchronous Communication:**
   - Services communicate via RabbitMQ queues
   - Non-blocking message processing
   - Reliable message delivery

### 2. **Service Decoupling:**
   - Hotel Service doesn't directly call Payment Service
   - Services can be scaled independently
   - Fault tolerance through message queuing

### 3. **Event-Driven Architecture:**
   - Booking events trigger payment processing
   - Payment events trigger booking confirmations
   - Loose coupling between services

### 4. **Message Serialization:**
   - JSON message format
   - Type-safe message handling
   - Automatic message conversion

### 5. **Error Handling:**
   - Payment failure handling
   - Booking cancellation on payment failure
   - Retry mechanisms (configurable)

## Service Dependencies

### Hotel Service:
- Spring Boot Web
- Spring Cloud Eureka Client
- Spring AMQP (RabbitMQ)
- PostgreSQL
- JPA/Hibernate

### Payment Service:
- Spring Boot Web
- Spring Cloud Eureka Client
- Spring AMQP (RabbitMQ)
- PostgreSQL
- JPA/Hibernate

## Environment Variables

### RabbitMQ Configuration:
```properties
spring.rabbitmq.host=rabbitmq
spring.rabbitmq.port=5672
spring.rabbitmq.username=admin
spring.rabbitmq.password=admin123
```

## Monitoring and Debugging

1. **Service Logs:** Check Docker container logs for message flow
2. **RabbitMQ Management:** http://localhost:15672
3. **Eureka Dashboard:** http://localhost:8761
4. **Service Health:** 
   - Hotel Service: http://localhost:8082/actuator/health
   - Payment Service: http://localhost:8084/actuator/health

## Payment Simulation

The Payment Service simulates payment processing with:
- 2-second processing delay
- 80% success rate (configurable)
- Automatic status updates
- Service notifications

This demonstrates real-world scenarios where payments can succeed or fail, and how the system handles both cases gracefully.
