FROM openjdk:17-jdk-slim
COPY target/price-catalog-api.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]