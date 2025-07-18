@echo off
echo Testing Travel API Microservices...
echo.

echo 1. Testing Eureka Server (Service Registry)...
curl -s "http://localhost:8761/eureka/apps" > nul
if %ERRORLEVEL% == 0 (
    echo ✓ Eureka Server is running on http://localhost:8761
) else (
    echo ✗ Eureka Server is not responding
)

echo.
echo 2. Testing API Gateway...
curl -s "http://localhost:8090/actuator/health" > nul
if %ERRORLEVEL% == 0 (
    echo ✓ API Gateway is running on http://localhost:8090
) else (
    echo ✗ API Gateway is not responding
)

echo.
echo 3. Testing Hotel Service...
curl -s "http://localhost:8082/actuator/health" > nul
if %ERRORLEVEL% == 0 (
    echo ✓ Hotel Service is running on http://localhost:8082
) else (
    echo ✗ Hotel Service is not responding
)

echo.
echo 4. Testing Transport Service...
curl -s "http://localhost:8083/actuator/health" > nul
if %ERRORLEVEL% == 0 (
    echo ✓ Transport Service is running on http://localhost:8083
) else (
    echo ✗ Transport Service is not responding
)

echo.
echo 5. Testing API Gateway Routes...
echo Testing Hotel Service via Gateway:
curl -s "http://localhost:8090/api/hotels/" > nul
if %ERRORLEVEL% == 0 (
    echo ✓ Hotel Service accessible via API Gateway
) else (
    echo ⚠ Hotel Service route may not be configured yet (normal if no endpoints exist)
)

echo.
echo Testing Transport Service via Gateway:
curl -s "http://localhost:8090/api/transports/" > nul
if %ERRORLEVEL% == 0 (
    echo ✓ Transport Service accessible via API Gateway
) else (
    echo ⚠ Transport Service route may not be configured yet (normal if no endpoints exist)
)

echo.
echo ================================
echo Service URLs:
echo - Eureka Dashboard: http://localhost:8761
echo - API Gateway: http://localhost:8090
echo - Hotel Service: http://localhost:8082
echo - Transport Service: http://localhost:8083
echo ================================
echo.
echo To view logs: docker-compose logs -f [service-name]
echo To stop services: docker-compose down
pause
