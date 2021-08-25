FROM openjdk:8-jdk-alpine

COPY ./target/simplewebapp-0.0.1-SNAPSHOT.jar /simple.jar

# RUN mvn -f ./META-INF/maven/com/mastery/simplewebapp/pom.xml clean package
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
CMD "java" "-jar" "simple.jar"


