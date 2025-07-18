Run \travel-api-ds\hotel-service> ./mvnw spring-boot:run

Test APIs:

# 1. Get all hotels

curl http://localhost:8082/api/hotels

# 2. Create a hotel (need location first)

curl -X POST http://localhost:8082/api/hotels \
 -H "Content-Type: application/json" \
 -d '{
"name": "Grand Palace Hotel",
"rating": 4.5,
"description": "Luxury hotel with amazing view",
"locationId": 1,
"photos": "hotel1.jpg,hotel2.jpg"
}'

# 3. Get hotel by ID

curl http://localhost:8082/api/hotels/1

# 4. Update hotel

curl -X PUT http://localhost:8082/api/hotels/1 \
 -H "Content-Type: application/json" \
 -d '{
"name": "Updated Grand Palace Hotel",
"rating": 4.8
}'
