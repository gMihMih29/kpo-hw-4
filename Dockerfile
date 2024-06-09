FROM postgres:latest
FROM openjdk:19

RUN mkdir /opt/app
COPY auth/build/libs/auth-1.0-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/auth-1.0-SNAPSHOT.jar"]