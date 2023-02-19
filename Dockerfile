FROM maven:3.8.7-eclipse-temurin-19 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:19-jdk-slim
COPY --from=build /target/io_server-0.0.1-SNAPSHOT.jar io_server.jar

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "io_server.jar" ]