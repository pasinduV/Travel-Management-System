@echo off
echo Stopping all Java processes...
taskkill /f /im java.exe >nul 2>&1

echo Waiting for processes to stop...
timeout /t 5 /nobreak >nul

echo Starting Eureka Server...
cd /d "d:\Git\FYP\travel-api-ds\eureka-server"
start "Eureka Server" java -jar target\eureka-server-0.0.1-SNAPSHOT.jar

echo Waiting for Eureka to start...
timeout /t 30 /nobreak >nul

echo Starting API Gateway...
cd /d "d:\Git\FYP\travel-api-ds\api-gateway"
start "API Gateway" java -jar target\api-gateway-0.0.1-SNAPSHOT.jar

echo Waiting for API Gateway to start...
timeout /t 20 /nobreak >nul

echo Starting Payment Service...
cd /d "d:\Git\FYP\travel-api-ds\payment-service"
start "Payment Service" java -jar target\payment-service-0.0.1-SNAPSHOT.jar

echo Starting User Service...
cd /d "d:\Git\FYP\travel-api-ds\user-service"
start "User Service" java -jar target\user-service-0.0.1-SNAPSHOT.jar

echo Starting Hotel Service...
cd /d "d:\Git\FYP\travel-api-ds\hotel-service"
start "Hotel Service" java -jar target\hotel-service-0.0.1-SNAPSHOT.jar

echo Starting Transport Service...
cd /d "d:\Git\FYP\travel-api-ds\transport-service"
start "Transport Service" java -jar target\transport-service-0.0.1-SNAPSHOT.jar

echo All services are starting...
echo Wait 60 seconds for all services to register with Eureka
echo Then test: http://localhost:8090/api/payments
echo.
echo Press any key to test endpoints...
pause

echo Testing endpoints...
echo.
echo Direct Payment Service:
curl -s http://localhost:8084/api/payments
echo.
echo.
echo Through API Gateway:
curl -s http://localhost:8090/api/payments
echo.
echo.
echo Done!
pause
