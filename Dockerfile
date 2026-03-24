FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw

COPY src src

RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
VOLUME /tmp

COPY --from=build /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-Xmx300m", "-jar", "app.jar"]