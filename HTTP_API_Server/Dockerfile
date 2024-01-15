FROM eclipse-temurin:latest
COPY target/HTTP_API_Server-1.0-SNAPSHOT-jar-with-dependencies.jar /app/app.jar

EXPOSE 7002
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
