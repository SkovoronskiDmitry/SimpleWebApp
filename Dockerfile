FROM openjdk:8-jdk-alpine

COPY ./target/simplewebapp-0.0.1-SNAPSHOT.jar /simple.jar

EXPOSE 8080

CMD "java" "-jar" "simple.jar"



