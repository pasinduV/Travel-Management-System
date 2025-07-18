@echo off
echo ================================
echo Testing Hotel Booking API with RabbitMQ Communication
echo ================================
echo.

echo 1. Testing Hotel Booking Creation (Hotel Service -> Payment Service via RabbitMQ)
echo.

curl -X POST "http://localhost:8082/api/bookings/hotel" ^
-H "Content-Type: application/json" ^
-d "{
    \"hotelId\": 1,
    \"roomId\": 1,
    \"userId\": 123,
    \"checkInDate\": \"2025-08-01\",
    \"checkOutDate\": \"2025-08-05\",
    \"guestName\": \"John Doe\",
    \"guestEmail\": \"john.doe@example.com\",
    \"guestPhone\": \"+1-555-0123\",
    \"numberOfGuests\": 2
}"

echo.
echo.
echo ================================
echo Hotel booking request sent!
echo This will:
echo 1. Create a hotel reservation in Hotel Service
echo 2. Send a message to Payment Service via RabbitMQ
echo 3. Payment Service will process payment (80%% success rate)
echo 4. Payment Service will notify Hotel Service back
echo 5. Hotel Service will update reservation status
echo.
echo Check the service logs to see the RabbitMQ message flow!
echo ================================

pause

echo.
echo.
echo 2. Testing Payment History
echo.

curl -X GET "http://localhost:8084/api/payments" ^
-H "Content-Type: application/json"

echo.
echo.
echo ================================
echo You can also check:
echo - RabbitMQ Management UI: http://localhost:15672 (admin/admin123)
echo - Hotel Service: http://localhost:8082/api/hotels
echo - Payment Service: http://localhost:8084/api/payments
echo ================================

pause
