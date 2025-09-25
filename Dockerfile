FROM gradle:jdk17-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
