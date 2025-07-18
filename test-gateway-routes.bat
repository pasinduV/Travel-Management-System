@echo off
echo ==============================================
echo Testing API Gateway Routes
echo ==============================================

echo.
echo Testing Eureka Registration...
curl -s http://localhost:8761/eureka/apps | findstr "<name>"

echo.
echo ==============================================
echo Testing Direct Service Access:
echo ==============================================

echo.
echo 1. Payment Service (Direct - Port 8084):
curl -s http://localhost:8084/api/payments

echo.
echo.
echo 2. User Service (Direct - Port 8085):
curl -s http://localhost:8085/api/users

echo.
echo ==============================================
echo Testing Through API Gateway (Explicit Routes):
echo ==============================================

echo.
echo 3. Payment Service (Gateway - Explicit Route):
curl -s http://localhost:8090/api/payments

echo.
echo.
echo 4. User Service (Gateway - Explicit Route):
curl -s http://localhost:8090/api/users

echo.
echo ==============================================
echo Testing Through API Gateway (Discovery Routes):
echo ==============================================

echo.
echo 5. Payment Service (Gateway - Discovery Route):
curl -s http://localhost:8090/payment-service/api/payments

echo.
echo.
echo 6. User Service (Gateway - Discovery Route):
curl -s http://localhost:8090/user-service/api/users

echo.
echo ==============================================
echo Testing API Gateway Health:
echo ==============================================
curl -s http://localhost:8090/actuator/health

echo.
echo.
echo ==============================================
echo Test Complete!
echo ==============================================
pause
