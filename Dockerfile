FROM openjdk:17
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]
EXPOSE 8080