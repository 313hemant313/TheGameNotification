FROM openjdk:18.0.1.1-jdk-buster
ARG BUILD_DATE
ARG BUILD_VERSION

LABEL maintainer="TheGameDefault"
LABEL org.label-schema.build-date=$BUILD_DATE
LABEL org.label-schema.version=$BUILD_VERSION
ARG JAR_FILE=target/the-game-notification-1.0.0-exec.jar

WORKDIR /opt/app

COPY ${JAR_FILE} application.jar

EXPOSE 80

ENTRYPOINT ["java","-jar","application.jar","--server.port=80"]