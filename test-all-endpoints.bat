@echo off
echo Testing all service endpoints...
echo.

echo Testing direct service endpoints:
echo.
echo Testing Hotel Service (direct):
curl -X GET http://localhost:8082/api/hotels
echo.
echo.

echo Testing Transport Service (direct):
curl -X GET http://localhost:8083/api/transports
echo.
echo.

echo Testing Payment Service (direct):
curl -X GET http://localhost:8084/api/payments
echo.
echo.

echo Testing User Service (direct):
curl -X GET http://localhost:8085/api/users
echo.
echo.

echo Testing through API Gateway:
echo.
echo Testing Hotels through Gateway:
curl -X GET http://localhost:8090/api/hotels
echo.
echo.

echo Testing Transports through Gateway:
curl -X GET http://localhost:8090/api/transports
echo.
echo.

echo Testing Payments through Gateway:
curl -X GET http://localhost:8090/api/payments
echo.
echo.

echo Testing Users through Gateway:
curl -X GET http://localhost:8090/api/users
echo.
echo.

echo All tests completed!
pause
