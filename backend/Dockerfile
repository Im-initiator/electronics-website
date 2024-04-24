# syntax=docker/dockerfile:1
FROM openjdk:21
WORKDIR /app
COPY .mvn/ .mvn
COPY pom.xml mvnw ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
CMD ["./mvnw", "spring-boot:run"]
EXPOSE 8080
