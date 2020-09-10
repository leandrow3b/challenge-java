FROM openjdk:11.0.3-jre-slim
COPY target/*.jar /usr/app/challenge-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/app/challenge-0.0.1-SNAPSHOT.jar"]
