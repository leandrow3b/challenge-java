FROM java:8-jdk-alpine
COPY target/*.jar /usr/app/challenge-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/app/challenge-0.0.1-SNAPSHOT.jar"]
