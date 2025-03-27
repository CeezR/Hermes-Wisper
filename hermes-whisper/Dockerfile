# Use a base image with JDK to build the app
FROM openjdk:24-jdk-slim AS builder

# Set working directory
WORKDIR /app

COPY pom.xml .  
COPY src ./src

# Build the application 
RUN mvn clean package -DskipTests

# Use an official OpenJDK runtime as the base image
FROM openjdk:24-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/*.jar app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]