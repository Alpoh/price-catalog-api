# Base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradlew ./gradlew
COPY gradle ./gradle
COPY build.gradle ./build.gradle
COPY settings.gradle ./settings.gradle

# Copy source code
COPY src ./src

# Grant execution permissions to Gradle wrapper
RUN chmod +x ./gradlew

# Build the application and ensure the JAR is created in the expected location
RUN ./gradlew clean build --no-daemon

# Copy the built JAR file from the Gradle build directory
COPY build/libs/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
