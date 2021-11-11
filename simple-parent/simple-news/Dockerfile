FROM openjdk:8u171-jre-alpine

WORKDIR /app

COPY target/*.jar app.jar

RUN sh -c 'mkdir keys' &&\
    sh -c 'mkdir logs' &&\
    sh -c 'chmod -R 777 keys/'
    sh -c 'chmod -R 777 logs/'

ENV GOOGLE_APPLICATION_CREDENTIALS=/keys/credentials.json
ENTRYPOINT ["java", "-Xms512m", "-Xmx512m", "-server", "-jar", "app.jar"]
