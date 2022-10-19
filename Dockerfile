FROM openjdk:11

LABEL maintainer="tmin.mee@gmail.com"
LABEL repository="https://github.com/teamsomnium/somnium-server.git"
LABEL version="0.0.1"

EXPOSE 8080

VOLUME /tmp

ARG JAR_FILE=build/libs/somnium-0.0.1.jar
COPY ${JAR_FILE} somnium.jar

ENTRYPOINT ["java", "-jar", "/somnium.jar"]