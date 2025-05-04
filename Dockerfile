FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app

COPY pom.xml ./
RUN apk add --no-cache maven && mvn dependency:resolve

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/spring-api-flyway-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]