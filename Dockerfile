FROM maven:3.8.5-openjdk-17 AS build
COPY itClusterjava2024/itClusterjava2024 .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/itClusterjava2024-0.0.1-SNAPSHOT.jar itClusterjava2024.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "itClusterjava2024.jar"]