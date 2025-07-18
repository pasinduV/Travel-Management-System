# Use Java base image
FROM openjdk:17-jdk-alpine

# Create app directory
WORKDIR /app

# Copy JAR file
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
