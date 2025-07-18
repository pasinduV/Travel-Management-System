@echo off
echo Starting Eureka Server first...
cd eureka-server
start "Eureka Server" cmd /k "mvn spring-boot:run"
echo Waiting for Eureka Server to start...
timeout /t 30 /nobreak

echo Starting API Gateway...
cd ..\api-gateway
start "API Gateway" cmd /k "mvn spring-boot:run"
echo Waiting for API Gateway to start...
timeout /t 15 /nobreak

echo Starting Hotel Service...
cd ..\hotel-service
start "Hotel Service" cmd /k "mvn spring-boot:run"
timeout /t 10 /nobreak

echo Starting Transport Service...
cd ..\transport-service
start "Transport Service" cmd /k "mvn spring-boot:run"
timeout /t 10 /nobreak

echo Starting Payment Service...
cd ..\payment-service
start "Payment Service" cmd /k "mvn spring-boot:run"
timeout /t 10 /nobreak

echo Starting User Service...
cd ..\user-service
start "User Service" cmd /k "mvn spring-boot:run"

echo All services started!
echo.
echo Services will be available at:
echo - Eureka Dashboard: http://localhost:8761
echo - API Gateway: http://localhost:8090
echo - Hotel Service: http://localhost:8082
echo - Transport Service: http://localhost:8083
echo - Payment Service: http://localhost:8084
echo - User Service: http://localhost:8085
echo.
echo API Gateway Routes:
echo - Hotels: http://localhost:8090/api/hotels
echo - Transports: http://localhost:8090/api/transports
echo - Payments: http://localhost:8090/api/payments
echo - Users: http://localhost:8090/api/users
pause
