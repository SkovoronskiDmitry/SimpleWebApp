FROM maven:3.6.3-jdk-11-slim

COPY ./target/simplewebapp-0.0.1-SNAPSHOT.jar /simple.jar

RUN mvn clean package

CMD "java" "-jar" "simple.jar"


