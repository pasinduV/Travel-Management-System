# Transport Service

Run \travel-api-ds\transport-service> ./mvnw spring-boot:run

**Note:** Service runs on port **8084** (updated from 8083 to resolve port conflicts)

Test APIs:

## Essential APIs

### 1. Get all transports

```bash
curl http://localhost:8083/api/transports
```

### 2. Create a transport (with new course inline)

```bash
curl -X POST http://localhost:8083/api/transports \
 -H "Content-Type: application/json" \
 -d '{
  "capacity": 200,
  "departureDate": "2025-08-20",
  "pricePerAdult": 250.0,
  "departureFromId": 1,
  "arrivalAtId": 2,
  "type": "PLANE"
}'
```

### 3. Get transport by ID

```bash
curl http://localhost:8083/api/transports/1
```

### 4. Create transport reservation

```bash
curl -X POST http://localhost:8083/api/transports/reservations \
 -H "Content-Type: application/json" \
 -d '{
  "transportId": 1,
  "numberOfSeats": 2
}'
```

## Transport Types Available

- `PLANE` - Air transportation
- `BUS` - Ground transportation
