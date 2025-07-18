@echo off
echo ================================
echo Starting Travel API with RabbitMQ
echo ================================
echo.

echo Stopping any existing containers...
docker-compose down

echo.
echo Building and starting all services...
docker-compose up -d

echo.
echo Waiting for services to start...
timeout /t 30 /nobreak > nul

echo.
echo ================================
echo Services Status:
echo ================================
echo Eureka Server: http://localhost:8761
echo API Gateway: http://localhost:8090
echo Hotel Service: http://localhost:8082
echo Transport Service: http://localhost:8083
echo Payment Service: http://localhost:8084
echo User Service: http://localhost:8085
echo RabbitMQ Management: http://localhost:15672 (admin/admin123)
echo.
echo ================================
echo RabbitMQ Booking API Demo:
echo ================================
echo.
echo To test the hotel booking with RabbitMQ communication:
echo 1. Run: test-rabbitmq-booking.bat
echo 2. Or use the API guide: RABBITMQ_API_GUIDE.md
echo.
echo The booking flow:
echo Hotel Service -> RabbitMQ -> Payment Service -> RabbitMQ -> Hotel Service
echo ================================

pause
