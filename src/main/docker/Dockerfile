FROM adoptopenjdk:11-jre-hotspot
MAINTAINER bits-pillani

ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]
