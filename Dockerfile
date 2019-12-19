FROM openjdk:8-jdk-alpine
COPY ./target/alphamon-0.1.0.jar /usr/src/alphamon/
WORKDIR /usr/src/alphamon
EXPOSE 8080
CMD ["java", "-jar", "alphamon-0.1.0.jar"]
