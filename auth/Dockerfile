FROM openjdk:8-jdk-alpine
COPY ./target/auth-0.0.1-SNAPSHOT.jar /usr/src/alphamon/
WORKDIR /usr/src/alphamon
EXPOSE 8080
CMD ["java", "-jar", "auth-0.0.1-SNAPSHOT.jar"]
