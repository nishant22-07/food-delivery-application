# ─────────────────────────────────────────────
# Stage 1 — Build
# ─────────────────────────────────────────────
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copy Maven wrapper & pom first (layer-cache dependencies)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw && ./mvnw dependency:go-offline -q

# Copy source and build the fat JAR
COPY src src
RUN ./mvnw package -DskipTests -q

# ─────────────────────────────────────────────
# Stage 2 — Runtime
# ─────────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

# Copy the fat JAR from the build stage
COPY --from=builder /app/target/*.jar app.jar

# Render injects PORT at runtime; Spring Boot reads it automatically
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
