# Stage 1: Build the application
FROM maven:3.9.5-amazoncorretto-11-debian-bookworm AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and install dependencies (this step is cached)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:23-rc-jdk-slim-bookworm

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/demo-*.jar /app/demo.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]