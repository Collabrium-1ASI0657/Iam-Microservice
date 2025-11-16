# ===========================
#   STAGE 1 : Build with Maven
# ===========================
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiar POM y descargar dependencias primero (cache)
COPY pom.xml .
RUN mvn -q dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Construir la aplicación
RUN mvn -q clean package -DskipTests

# ===========================
#   STAGE 2 : Run App
# ===========================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiamos el .jar desde el stage anterior
COPY --from=builder /app/target/*.jar app.jar

# Render asigna dinámicamente el puerto
ENV PORT=8081

# Variables necesarias para DB y JWT
ENV POSTGRES_URL=""
ENV POSTGRES_USER=""
ENV POSTGRES_PASSWORD=""
ENV JWT_SECRET=""

# Spring Boot debe usar el port asignado por Render
ENTRYPOINT ["sh", "-c", "java -jar -Dserver.port=${PORT} app.jar"]
