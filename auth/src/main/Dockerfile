# Use the official lightweight Java image.
# https://hub.docker.com/_/openjdk
FROM openjdk:11-jdk-slim as build

# Set the working directory inside the container
WORKDIR /app

# Copy the gradle configuration files
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Load all dependencies
RUN ./gradlew clean build --no-daemon > /dev/null 2>&1 || true

# Copy the source code
COPY src /app/src

# Build the application
RUN ./gradlew build -x test

# Run the web service on container startup.
FROM openjdk:11-jre-slim
COPY --from=build /app/build/libs/*.jar /app/auth-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/auth-service.jar"]
