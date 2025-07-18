@echo off
echo ==============================================
echo Starting Travel API Services
echo ==============================================

echo Stopping any existing Java processes...
taskkill /f /im java.exe >nul 2>&1

echo.
echo Starting Eureka Server...
cd /d "d:\Git\FYP\travel-api-ds\eureka-server"
start "Eureka-Server" cmd /c "java -jar target\eureka-server-0.0.1-SNAPSHOT.jar"

echo Waiting 30 seconds for Eureka to start...
timeout /t 30 /nobreak >nul

echo.
echo Starting API Gateway...
cd /d "d:\Git\FYP\travel-api-ds\api-gateway"
start "API-Gateway" cmd /c "java -jar target\api-gateway-0.0.1-SNAPSHOT.jar"

echo Waiting 20 seconds for API Gateway to start...
timeout /t 20 /nobreak >nul

echo.
echo Starting Payment Service...
cd /d "d:\Git\FYP\travel-api-ds\payment-service"
start "Payment-Service" cmd /c "java -jar target\payment-service-0.0.1-SNAPSHOT.jar"

echo.
echo Starting User Service...
cd /d "d:\Git\FYP\travel-api-ds\user-service"
start "User-Service" cmd /c "java -jar target\user-service-0.0.1-SNAPSHOT.jar"

echo.
echo Starting Hotel Service...
cd /d "d:\Git\FYP\travel-api-ds\hotel-service"
start "Hotel-Service" cmd /c "java -jar target\hotel-service-0.0.1-SNAPSHOT.jar"

echo.
echo Starting Transport Service...
cd /d "d:\Git\FYP\travel-api-ds\transport-service"
start "Transport-Service" cmd /c "java -jar target\transport-service-0.0.1-SNAPSHOT.jar"

echo.
echo ==============================================
echo All services are starting...
echo Please wait 60 seconds for services to register
echo ==============================================
echo.
echo Available endpoints after startup:
echo - Eureka Dashboard: http://localhost:8761
echo - API Gateway Health: http://localhost:8090/actuator/health
echo - Direct Payment: http://localhost:8084/api/payments
echo - Direct User: http://localhost:8085/api/users
echo - Gateway Payment: http://localhost:8090/api/payments
echo - Gateway User: http://localhost:8090/api/users
echo.
pause
