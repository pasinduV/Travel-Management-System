# Payment Service

Run `\travel-api-ds\payment-service> ./mvnw spring-boot:run`

**Note:** Service runs on port **8084**

Test APIs:

## Essential APIs

### 1. Get all payments

```bash
curl http://localhost:8084/api/payments
```

### 2. Create a payment

```bash
curl -X POST http://localhost:8084/api/payments \
 -H "Content-Type: application/json" \
 -d '{
  "reservationId": 1,
  "userId": 1,
  "roomReservationId": 1,
  "transportReservationId": null,
  "amount": 150.00,
  "method": "CARD"
}'
```

### 3. Get payment by ID

```bash
curl http://localhost:8084/api/payments/1
```

### 4. Update payment status

```bash
curl -X PUT http://localhost:8084/api/payments/1 \
 -H "Content-Type: application/json" \
 -d '{
  "status": "SUCCESS",
  "paid": true
}'
```

## Additional APIs

### Get payments by user ID

```bash
curl http://localhost:8084/api/payments/user/1
```

### Get payments by reservation ID

```bash
curl http://localhost:8084/api/payments/reservation/1
```

### Get payments by status

```bash
curl http://localhost:8084/api/payments/status/PENDING
curl http://localhost:8084/api/payments/status/SUCCESS
curl http://localhost:8084/api/payments/status/FAILED
```

### Delete payment

```bash
curl -X DELETE http://localhost:8084/api/payments/1
```

## Payment Status Options

- `PENDING` - Payment is waiting to be processed
- `SUCCESS` - Payment completed successfully
- `FAILED` - Payment failed

## Payment Method Options

- `CARD` - Credit/Debit card payment
- `UPI` - UPI payment
- `WALLET` - Digital wallet payment

## Database Schema

The service uses the following table structure:

```sql
CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    reservation_id INT NOT NULL,
    user_id INT NOT NULL,
    room_reservation_id INT,
    transport_reservation_id INT,
    amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'SUCCESS', 'FAILED')),
    method VARCHAR(30) NOT NULL,
    paid BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
