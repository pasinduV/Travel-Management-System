@echo off
echo Building and Starting Travel API Microservices...

echo.
echo Building Eureka Server...
cd eureka-server
call mvnw.cmd clean package -DskipTests
cd ..

echo.
echo Building API Gateway...
cd api-gateway
call mvnw.cmd clean package -DskipTests
cd ..

echo.
echo Building Hotel Service...
cd hotel-service
call mvnw.cmd clean package -DskipTests
cd ..

echo.
echo Building Transport Service...
cd transport-service
call mvnw.cmd clean package -DskipTests
cd ..

echo.
echo Starting all services with Docker Compose...
docker-compose up -d --build

echo.
echo All services are starting...
echo Eureka Server will be available at: http://localhost:8761
echo API Gateway will be available at: http://localhost:8090
echo Hotel Service will be available at: http://localhost:8082
echo Transport Service will be available at: http://localhost:8083
echo.
echo To view logs: docker-compose logs -f
echo To stop services: docker-compose down
pause
